package br.ufpb.dcx.project.dto;

import br.ufpb.dcx.project.model.Campaign;

public class CampaignStatusDTO {

    private boolean status;
    private String title;
    private String description;


    public CampaignStatusDTO getCampaignStatusDTO(Campaign campaign){
        this.status = campaign.isStatus();
        this.title = campaign.getTitle();
        this.description = campaign.getDescription();
        return this;
    }
}
