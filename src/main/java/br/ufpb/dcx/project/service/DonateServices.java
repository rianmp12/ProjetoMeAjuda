package br.ufpb.dcx.project.service;


import br.ufpb.dcx.project.dto.DonationListDTO;
import br.ufpb.dcx.project.model.Donation;
import br.ufpb.dcx.project.repository.RepositoryDonate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class DonateServices {

    @Autowired
    private RepositoryDonate repositoryDonate;

    public List<DonationListDTO> getDonations(){
        List<Donation> donations = repositoryDonate.findAllByOrderByDateDonateAsc();
        List<DonationListDTO> dtoDonations = new ArrayList<>();
        for (Donation d: donations){
            dtoDonations.add(new DonationListDTO(d.getCampaign().getTitle(),d.getDonate(),d.getDateDonate()));
        }
        return dtoDonations;
    }
}