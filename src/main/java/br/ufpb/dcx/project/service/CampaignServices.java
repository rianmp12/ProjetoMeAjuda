package br.ufpb.dcx.project.service;

import br.ufpb.dcx.project.dto.CampaignStatusDTO;
import br.ufpb.dcx.project.enuns.Papel;
import br.ufpb.dcx.project.exception.CampaignExpiredException;
import br.ufpb.dcx.project.exception.CampaignNotFoundException;
import br.ufpb.dcx.project.exception.UnauthorizedOperationException;
import br.ufpb.dcx.project.model.Campaign;
import br.ufpb.dcx.project.model.Donate;
import br.ufpb.dcx.project.model.User;
import br.ufpb.dcx.project.repository.RepositoryCampaign;
import br.ufpb.dcx.project.repository.RepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import static java.time.LocalDateTime.*;

@Service
public class CampaignServices {

    @Autowired
    private RepositoryCampaign repositoryCampaign;

    @Autowired
    private RepositoryUser repositoryUser;

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
            if (campaign.getDeadlineDate().after(new Date())) {
                newCampaign.setId(campaign.getId());
                repositoryCampaign.save(newCampaign);
                return newCampaign;
            } else {
                throw  new CampaignExpiredException("A campanha já expirou e não pode ser editada.");
            }
        }
        throw  new UnauthorizedOperationException("Esse usuário não tem permissão para realizar essa operação.");
    }

    public CampaignStatusDTO removeCampaign(Long id, String header){
        User userLog = userServices.getUser(serviceAuthJWT.getSujeitoDoToken(header));
        Campaign campaign = this.repositoryCampaign.findById(id)
                .orElseThrow(() -> new CampaignNotFoundException("Campanha não encontrada com esse id:" + id));
        if (campaign.getUser().getId().equals(userLog.getId()) || userLog.getPapel().equals(Papel.ADMIN)){
            campaign.setStatus(false);
            userLog.getCampaignByID(id).setStatus(false);
            repositoryUser.save(userLog);
            repositoryCampaign.save(campaign);
            return new CampaignStatusDTO().getCampaignStatusDTO(campaign);
        }
        throw  new UnauthorizedOperationException("Esse usuário não tem permissão para realizar essa operação.");
    }


    public Donate addDonate(Long id, Double value, String header){
        User userLog = userServices.getUser(serviceAuthJWT.getSujeitoDoToken(header));
        Campaign campaign = this.repositoryCampaign.findById(id)
                .orElseThrow(() -> new CampaignNotFoundException("Campanha não encontrada com esse id:" + id));
        if (campaign.getUser().getId().equals(userLog.getId()) ||userLog.getPapel().equals(Papel.ADMIN)){
            Donate donate = new Donate();
            donate.getDonate(campaign, userLog, value);
            campaign.addDonate(donate);
            userLog.getDonates().add(donate);
            repositoryCampaign.save(campaign);
            repositoryUser.save(userLog);
            return donate;
        }
        throw  new UnauthorizedOperationException("Esse usuário não tem permissão para realizar essa operação.");
    }
}