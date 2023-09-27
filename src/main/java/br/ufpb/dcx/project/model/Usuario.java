package br.ufpb.dcx.project.model;

import br.ufpb.dcx.project.enuns.Papel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    @UniqueElements
    private String email;
    private String password;
    private String tell;

    private String identidade;

    private Papel papel = Papel.USER;




    public Usuario getUser(DTOPostUsuario userPost){
        this.name = userPost.getName();
        this.email = userPost.getEmail();
        this.password = userPost.getPassword();
        this.papel = userPost.getPapel();
        return this;
    }

    public void addComment(Comentario comment){
        this.getComments().add(comment);
        comment.setUser(this);
    }


}
