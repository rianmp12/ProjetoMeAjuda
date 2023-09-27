package br.ufpb.dcx.project.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private boolean status;
    @Size(max = 100, message = "A mensagem não deve ter mais de 100 caracteres")
    @NotNull
    @NotEmpty
    private String title;
    @NotNull
    @NotEmpty
    @Size(max = 1000, message = "A mensagem não deve ter mais de 1000 caracteres.")
    private String description;
    @Positive(message = "O valor deve ser maior que zero")
    private Double meta;

    @OneToMany(mappedBy = "campaign")
    private List<Donate> collected;
    @PastOrPresent(message = "A data de criação da campanha deve estar no passado ou no presente")
    private Date creationDate;
    @Future(message = "A data de prazo para finalização da campanha deve estar no futuro")
    private Date deadlineDate;
}