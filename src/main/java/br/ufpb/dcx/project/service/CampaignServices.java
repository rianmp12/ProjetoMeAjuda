package br.ufpb.dcx.project.service;

import br.ufpb.dcx.project.dto.CampaignStatusDTO;
import br.ufpb.dcx.project.dto.DonationDTO;
import br.ufpb.dcx.project.enums.Papel;
import br.ufpb.dcx.project.exception.*;
import br.ufpb.dcx.project.model.Campaign;
import br.ufpb.dcx.project.model.Donation;
import br.ufpb.dcx.project.model.User;
import br.ufpb.dcx.project.repository.RepositoryCampaign;
import br.ufpb.dcx.project.repository.RepositoryDonate;
import br.ufpb.dcx.project.repository.RepositoryUser;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CampaignServices {

    @Autowired
    private RepositoryCampaign repositoryCampaign;

    @Autowired
    private RepositoryUser repositoryUser;

    @Autowired
    private UserServices userServices;

    @Autowired
    private RepositoryDonate donateRepository;

    @Autowired
    private ServiceAuthJWT serviceAuthJWT;

    public Campaign addCampaign(String header, Campaign newCampaign){
        User userLog = userServices.getUser(serviceAuthJWT.getSujeitoDoToken(header));
        newCampaign.setUser(userLog);
        repositoryCampaign.save(newCampaign);
        return newCampaign;
    }

    public Campaign editCampaign(Long id, String header, Campaign newCampaign){
        User userLog = userServices.getUser(serviceAuthJWT.getSujeitoDoToken(header));
        Campaign campaign = this.repositoryCampaign.findById(id)
                .orElseThrow(() -> new CampaignNotFoundException("Campanha não encontrada com esse id:" + id));
        if (campaign.getUser().getId().equals(userLog.getId())){
            if (campaign.checkAndUpdateStatus()) {
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
        if (campaign.getUser().getId().equals(userLog.getId()) || userLog.getPapel().equals(Papel.ADMIN) ){
            if (campaign.getCollected().isEmpty()) {
                campaign.setRemoved(true);
                repositoryCampaign.save(campaign);
                return new CampaignStatusDTO().getCampaignStatusDTO(campaign);
            } else {
                throw new CampaignNotCloseException("Essa campanha não pode ser encerrada");
            }
        }
        throw new UnauthorizedOperationException("Esse usuário não tem permissão para realizar essa operação.");
    }


    public DonationDTO addDonate(Long id, Donation donate, String header){
        User userLog = userServices.getUser(serviceAuthJWT.getSujeitoDoToken(header));
        Campaign campaign = this.repositoryCampaign.findById(id)
                .orElseThrow(() -> new CampaignNotFoundException("Campanha não encontrada com esse id:" + id));
        if (campaign.checkAndUpdateStatus() && donate.getDonate() > 0.0){
            donate.setUser(userLog);
            donate.setCampaign(campaign);
            userLog.getDonates().add(donate);
            repositoryCampaign.save(campaign);
            repositoryUser.save(userLog);
            DonationDTO donateDTO = new DonationDTO();
            donateDTO.setName(donate.getUser().getName());
            donateDTO.setNameCampaign(donate.getCampaign().getTitle());
            donateDTO.setDonate(donate.getDonate());
            donateDTO.setId(donate.getId());
            donateRepository.save(donate);
            return donateDTO;
        } else {
            throw new InvalidDonateException("Uma doação não pode ser atribuida a essa capanha.");
        }
    }
    public List<Campaign> getCampaignActive() {
        List<Campaign> campaigns = repositoryCampaign.findByStatusTrueAndRemovedFalseOrderByTitleAsc();
        return campaigns;
    }
    public List<Campaign> getCampaignActiveOrderDate() {
         List<Campaign> campaigns = repositoryCampaign.findByStatusTrueAndRemovedFalseOrderByCreationDateAsc();
        return campaigns;
    }

    public List<Campaign> getCampaignClosedOrderDate() {
         List<Campaign> campaigns = repositoryCampaign.findByStatusFalseAndRemovedFalseOrderByCreationDateAsc();
        return campaigns;
    }

    public List<Campaign> getCampaignGoal() {
         List<Campaign> campaigns = repositoryCampaign.findByAchieveGoal();
        return campaigns;
    }
}