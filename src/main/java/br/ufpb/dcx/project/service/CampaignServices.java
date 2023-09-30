package br.ufpb.dcx.project.service;

import br.ufpb.dcx.project.enuns.Papel;
import br.ufpb.dcx.project.exception.CampaignNotFoundException;
import br.ufpb.dcx.project.exception.UnauthorizedOperationException;
import br.ufpb.dcx.project.exception.UserNotFoundException;
import br.ufpb.dcx.project.model.Campaign;
import br.ufpb.dcx.project.model.User;
import br.ufpb.dcx.project.repository.RepositoryCampaign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CampaignServices {

    @Autowired
    private RepositoryCampaign repositoryCampaign;
    @Autowired
    private UserServices userServices;

    @Autowired
    private ServiceAuthJWT serviceAuthJWT;


    public Campaign addCampaign(String header, Campaign newCampaign){
        User userLog = userServices.getUser(serviceAuthJWT.getSujeitoDoToken(header));
            repositoryCampaign.save(newCampaign);
            return newCampaign;
    }

    public Campaign editCampaign(Long id, String header, Campaign newCampaign){
        User userLog = userServices.getUser(serviceAuthJWT.getSujeitoDoToken(header));
        Campaign campaign = this.repositoryCampaign.findById(id)
                .orElseThrow(() -> new CampaignNotFoundException("Campanha não encontrada com esse id:" + id));
        if (campaign.getUser().getId().equals(userLog.getId())){
            newCampaign.setId(campaign.getId());
            repositoryCampaign.save(newCampaign);
        }
        return newCampaign;
    }


}
