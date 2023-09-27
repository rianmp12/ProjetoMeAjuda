package br.ufpb.dcx.project.model;


import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Positive;

public class Donate {
    @OneToMany(mappedBy = "user")
    private User user;
    @Positive(message = "O valor deve ser maior que zero")
    private Double donate;
}
