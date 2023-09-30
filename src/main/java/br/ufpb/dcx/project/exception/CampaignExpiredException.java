package br.ufpb.dcx.project.exception;

public class CampaignExpiredException extends RuntimeException{
    public CampaignExpiredException(String message){
        super(message);
    }
}
