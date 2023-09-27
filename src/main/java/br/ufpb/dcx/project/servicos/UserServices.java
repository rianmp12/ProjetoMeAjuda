package br.ufpb.dcx.project.servicos;

import br.ufpb.dcx.project.dto.UserLoginDTO;
import br.ufpb.dcx.project.repository.RepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServices {

    @Autowired
    private RepositoryUser repositoryUsers;
    @Autowired
    private ServiceAuthJWT serviceAuthJWT;




    public boolean validateUserPassword(UserLoginDTO user) {
        Optional<User> optUsuario = repositoryUser.findByEmail(user.getEmail());
        return optUsuario.isPresent() && optUsuario.get().getPassword().equals(user.getPassword());
    }
}
