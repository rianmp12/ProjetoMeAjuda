package br.ufpb.dcx.project.controller;

import br.ufpb.dcx.project.dto.CampaignStatusDTO;
import br.ufpb.dcx.project.model.Campaign;
import br.ufpb.dcx.project.model.Donate;
import br.ufpb.dcx.project.service.CampaignServices;

import java.util.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class CampaignController {

    private CampaignServices campaignServices;



    @PostMapping("/auth/api/campaign")
    public ResponseEntity<Campaign> addCampaign(@RequestBody Campaign campaign, @RequestHeader("Authorization") String header){
        return ResponseEntity.ok(campaignServices.addCampaign(header, campaign));
    }

    @PutMapping("/auth/api/campaign/{id}")
    public ResponseEntity<Campaign> editCampaign(@PathVariable Long id, @RequestBody Campaign newCampaign, @RequestHeader("Authorization") String header){
        return ResponseEntity.ok(campaignServices.editCampaign(id, header, newCampaign));
    }

    @DeleteMapping("/auth/api/campaign/{id}")
    public ResponseEntity<CampaignStatusDTO> removeCampaign(@PathVariable Long id, @RequestHeader("Authorization") String header){
        return ResponseEntity.ok(campaignServices.removeCampaign(id, header));
    }

    @PostMapping("/auth/api/campaign/donate/{id}")
    public ResponseEntity<Donate> addDonate(@PathVariable Long id, @RequestHeader("Authorization") String header, @RequestBody Double value){
        return ResponseEntity.ok(campaignServices.addDonate(id,value, header));
    }

    @GetMapping("/api/campaign/active")
    public ResponseEntity<List<Campaign>> getCampaignActive(){
        return ResponseEntity.ok(campaignServices.getCampaignActive());
    }

}
