package br.ufpb.dcx.project.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Campanha {
    @Id
    @GeneratedValue
    private String id;
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
    private Double arrecadado;
    @PastOrPresent(message = "A data de criação da campanha deve estar no passado ou no presente")
    private Date dataDeCriacao;
    @Future(message = "A data de prazo para finalização da campanha deve estar no futuro")
    private Date dataDePrazo;
}