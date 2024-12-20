package br.ufpb.dcx.project.controller;

import br.ufpb.dcx.project.dto.CampaignStatusDTO;
import br.ufpb.dcx.project.dto.DonationDTO;
import br.ufpb.dcx.project.dto.DonationListDTO;
import br.ufpb.dcx.project.model.Campaign;
import br.ufpb.dcx.project.model.Donation;
import br.ufpb.dcx.project.service.CampaignServices;
import br.ufpb.dcx.project.service.DonateServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.*;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class CampaignController {

    @Autowired
    private CampaignServices campaignServices;

    @Autowired
    private DonateServices donateServices;


    @PostMapping("auth/api/campaign")
    @Operation(summary = "Adiciona uma campanha com um usuário devidamente cadastrado.",
            description = "É possivel adicionar uma campanha quando o usuário esta logado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Campanha adicionada com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário sem permissão.")
    })
    public ResponseEntity<Campaign> addCampaign(@RequestBody Campaign campaign, @RequestHeader("Authorization") String header){
        return ResponseEntity.ok(campaignServices.addCampaign(header, campaign));
    }

    @PutMapping("/auth/api/campaign/{id}")
    @Operation(summary = "Atualiza uma campanha com um usuário devidamente cadastrado.",
            description = "É possivel atualizar uma campanha quando o usuário esta logado e dono da campanha."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Campanha atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Campanha não encontrada."),
            @ApiResponse(responseCode = "403", description = "Campanha expirada.")
    })
    public ResponseEntity<Campaign> editCampaign(@PathVariable Long id, @RequestBody Campaign newCampaign, @RequestHeader("Authorization") String header){
        return ResponseEntity.ok(campaignServices.editCampaign(id, header, newCampaign));
    }

    @DeleteMapping("/auth/api/campaign/{id}")
    @Operation(summary = "Remove uma campanha com um usuário devidamente cadastrado.",
            description = "É possivel remover uma campanha quando o usuário esta logado e dono da campanha."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Campanha removida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Campanha não encontrada."),
            @ApiResponse(responseCode = "403", description = "Campanha não pode ser encerrada."),
            @ApiResponse(responseCode = "403", description = "Usuário não tem permissão.")
    })
    public ResponseEntity<CampaignStatusDTO> removeCampaign(@PathVariable Long id, @RequestHeader("Authorization") String header){
        return ResponseEntity.ok(campaignServices.removeCampaign(id, header));
    }

    @PostMapping("/auth/api/campaign/donation/{id}")
    @Operation(summary = "Adiciona uma doação com um usuário devidamente logado.",
            description = "É possivel adicionar uma doação quando o usuário esta logado e valor maior que 0."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Doação realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Campanha não encontrada."),
            @ApiResponse(responseCode = "400", description = "Uma doação não pode ser atribuida a essa campanha."),
            @ApiResponse(responseCode = "403", description = "Usuário não tem permissão.")
    })
    public ResponseEntity<DonationDTO> addDonate(@PathVariable Long id, @RequestHeader("Authorization") String header, @RequestBody Donation value){
        return ResponseEntity.ok(campaignServices.addDonate(id,value, header));
    }

    @GetMapping("/api/campaign/active")
    @Operation(summary = "Listar campanhas ativas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Campanha listada.",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Campanha não listada.",
                    content = @Content)
    })
    public ResponseEntity<List<Campaign>> getCampaignActiveTitle(){
        return ResponseEntity.ok(campaignServices.getCampaignActive());
    }

    @GetMapping("/api/campaign/active/orderDate")
    @Operation(summary = "Ativa a campanha por ordem de data.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Campanha ativada na ordem da data.",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Campanha não encontrada.",
                    content = @Content)
    })
    public ResponseEntity<List<Campaign>> getCampaignActiveOrderDate(){
        return ResponseEntity.ok(campaignServices.getCampaignActiveOrderDate());
    }

    @GetMapping("/api/campaign/closed/orderDate")
    @Operation(summary = "Listar campanhas encerradas por ordem de data.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Campanha encerrada listada com base na ordem.",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Campanha não encontrada.",
                    content = @Content)
    })
    public ResponseEntity<List<Campaign>> getCampaignClosedOrderDate(){
        return ResponseEntity.ok(campaignServices.getCampaignClosedOrderDate());
    }

    @GetMapping("/api/campaign/goal")
    @Operation(summary = "Lista as campanhas com meta concluida.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Campanha listada com sucesso.",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Campanha não encontrada.",
                    content = @Content)
    })
    public ResponseEntity<List<Campaign>> getCampaignGoal(){
        return ResponseEntity.ok(campaignServices.getCampaignGoal());
    }

    @GetMapping("api/donations")
    public ResponseEntity<List<DonationListDTO>> getDonations(){
        return ResponseEntity.ok(donateServices.getDonations());
    }
}