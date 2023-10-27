package br.ufpb.dcx.project.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class DonationListDTO {

    private String title;
    private Double value;
    private LocalDateTime date;


    public DonationListDTO(String title, Double value, LocalDateTime date){
        this.title = title;
        this.date = date;
        this.value = value;
    }
}
