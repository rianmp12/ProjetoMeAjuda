package br.ufpb.dcx.project.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DonationDTO {

    private Long id;
    private String name;
    private String nameCampaign;
    private Double donate;
}
