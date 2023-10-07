package br.ufpb.dcx.project.dto;

import br.ufpb.dcx.project.enums.Papel;
import br.ufpb.dcx.project.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String email;
    private String name;
    private Papel papel = Papel.REGULAR;


    public UserDTO getDTOUser(User user){
        this.email = user.getEmail();
        this.name = user.getName();
        this.papel =  user.getPapel();
        return this;
    }

    public static UserDTO from(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setPapel(user.getPapel());
        userDTO.setName(user.getName());
        return userDTO;
    }
}
