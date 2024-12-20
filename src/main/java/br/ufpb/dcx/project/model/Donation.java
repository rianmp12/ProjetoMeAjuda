package br.ufpb.dcx.project.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "campaign_id")
    private Campaign campaign;
    @Positive(message = "O valor deve ser maior que zero")
    private Double donate;
    private LocalDateTime dateDonate = LocalDateTime.now();


    public Donation getDonate(Campaign campaign, User user, Double value){
        this.user = user;
        this.donate = value;
        this.campaign = campaign;
        this.dateDonate = LocalDateTime.now();
        return this;
    }
}
