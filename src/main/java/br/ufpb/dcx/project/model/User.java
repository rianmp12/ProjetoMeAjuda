package br.ufpb.dcx.project.model;

import br.ufpb.dcx.project.dto.PostUserDTO;
import br.ufpb.dcx.project.enums.Papel;
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
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String email;
    private String password;
    private String tell;
    private String entity;
    private String userClass;
    private Papel papel = Papel.REGULAR;

    private boolean statusUser = true;

    @OneToMany(mappedBy = "user")
    private List<Campaign> campaigns;

    @OneToMany(mappedBy = "user")
    private List<Donation> donates;

    public User getUser(PostUserDTO userPost){
        this.name = userPost.getName();
        this.email = userPost.getEmail();
        this.password = userPost.getPassword();
        this.papel = userPost.getPapel();
        return this;
    }

    public Campaign getCampaignByID(Long id){

        for (Campaign c: this.campaigns){
            if (c.getId() == id){
                return c;
            }
        }
        return null;
    }


}
