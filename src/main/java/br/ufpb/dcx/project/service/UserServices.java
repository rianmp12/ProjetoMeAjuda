package br.ufpb.dcx.project.service;

import br.ufpb.dcx.project.dto.PostUserDTO;
import br.ufpb.dcx.project.dto.UserDTO;
import br.ufpb.dcx.project.dto.UserLoginDTO;
import br.ufpb.dcx.project.enuns.Papel;
import br.ufpb.dcx.project.exception.UnauthorizedOperationException;
import br.ufpb.dcx.project.exception.UserExistException;
import br.ufpb.dcx.project.exception.UserNotFoundException;
import br.ufpb.dcx.project.model.User;
import br.ufpb.dcx.project.repository.RepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServices {

    @Autowired
    private RepositoryUser repositoryUsers;
    @Autowired
    private ServiceAuthJWT serviceAuthJWT;


    public List<UserDTO> getAllUserDTO(){
        List<User> users =  repositoryUsers.findAll();

        List<UserDTO> dtoUsers = new ArrayList<>();
        for(User user: users){
            dtoUsers.add(new UserDTO().getDTOUser(user));
        }

        return dtoUsers;
    }
    public UserDTO addUser(PostUserDTO user){
        Optional<User> userExist  = repositoryUsers.findByEmail(user.getEmail());
        if (userExist.isPresent()) {
            throw new UserExistException("Usuário já existe com esse email.");
        }
        else{
            User userAdd = new User().getUser(user);
            repositoryUsers.save(userAdd);
            return new UserDTO().getDTOUser(userAdd);
        }
    }

    public UserDTO updateUser(User newUser, String email, String header){
        User userLog = this.getUser(serviceAuthJWT.getSujeitoDoToken(header));

        User user = repositoryUsers.findByEmail(email)
                .orElseThrow(()-> new UserNotFoundException("Usuário não encontrado."));

        if(userLog.getPapel().equals(Papel.ADMIN) || userLog.getEmail().equals(user.getEmail())){
            newUser.setId(user.getId());
            repositoryUsers.save(newUser);
            return new UserDTO().getDTOUser(newUser);
        }else{
            throw  new UnauthorizedOperationException("Esse usuário não tem autorização para realizar esta ação.");
        }

    }

    public UserDTO recoveryUser(String email, String authHeader){
        Optional<User> user = repositoryUsers.findByEmail(email);
        if(user.isPresent() && checkUserPermission(authHeader, email)){
            return UserDTO.from(user.get());
        }
        throw new UnsupportedOperationException("Usuário não tem permissão.");
    }

    public UserDTO removeUser(String email, String authHeader) {
        User userDelete = this.getUser(email);
        User userLog = this.getUser(serviceAuthJWT.getSujeitoDoToken(authHeader));
        if(userLog.getPapel().equals(Papel.ADMIN) || userLog.getEmail().equals(userDelete.getEmail()))
            repositoryUsers.delete(userDelete);
        else throw new UnsupportedOperationException("Usuário não tem permissão.");
        return UserDTO.from(userDelete);
    }

    public boolean checkUserPermission(String authorizationHeader, String email) {
        String subject = serviceAuthJWT.getSujeitoDoToken(authorizationHeader);
        Optional<User> optUsuario = repositoryUsers.findByEmail(subject);
        return optUsuario.get().getPapel().equals(Papel.ADMIN) || optUsuario.get().getEmail().equals(email);
    }

    public User getUser(String email) {
        Optional<User> optUser = repositoryUsers.findByEmail(email);
        if (optUser.isPresent()) {
            return optUser.get();
        }
        throw new NoSuchElementException("Usuário não existe: " + email + ".");
    }



    public boolean validateUserPassword(UserLoginDTO user) {
        Optional<User> optUsuario = repositoryUsers.findByEmail(user.getEmail());
        return optUsuario.isPresent() && optUsuario.get().getPassword().equals(user.getPassword());
    }
}
