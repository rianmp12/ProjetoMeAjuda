package br.ufpb.dcx.project.exception;

public class CampaignNotFoundException extends RuntimeException{
    public CampaignNotFoundException(String message) {
        super(message);
    }
}
