package br.ufpb.dcx.project.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    @JsonIgnore
    private User user;

    private boolean status = true;

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

    @OneToMany(mappedBy = "campaign", cascade = CascadeType.ALL)
    private List<Donation> collected = new ArrayList<>();

    @PastOrPresent(message = "A data de criação da campanha deve estar no passado ou no presente")
    private LocalDateTime creationDate = LocalDateTime.now();

    @Future(message = "A data de prazo para finalização da campanha deve estar no futuro")
    private LocalDateTime deadlineDate;

    private boolean removed = false;

    public Donation addDonate(Donation donate){
        this.collected.add(donate);
        return donate;
    }

    public Double donateCollected(){
        Double collection = 0.0;
        for (Donation d: this.collected){
            collection+= d.getDonate();
        }
        return collection;
    }


    public boolean checkAndUpdateStatus() {
        LocalDateTime currentDate = LocalDateTime.now();
        if (deadlineDate != null && deadlineDate.isBefore(currentDate)) {
            status = false;
        }
        return isStatus();
    }
}