package br.ufpb.dcx.project.dto;


import br.ufpb.dcx.project.enuns.Papel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostUserDTO {

    private String name;
    private String email;
    private String password;
    private Papel papel = Papel.USER;
}
