package br.ufpb.dcx.project.model;

import br.ufpb.dcx.project.dto.PostUserDTO;
import br.ufpb.dcx.project.enuns.Papel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    @UniqueElements
    private String email;
    private String password;
    private String tell;

    private String entity;
    private String userClass;
    private Papel papel = Papel.USER;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Campaign> campaigns;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Donate> donates;

    public User getUser(PostUserDTO userPost){
        this.name = userPost.getName();
        this.email = userPost.getEmail();
        this.password = userPost.getPassword();
        this.papel = userPost.getPapel();
        return this;
    }


}
