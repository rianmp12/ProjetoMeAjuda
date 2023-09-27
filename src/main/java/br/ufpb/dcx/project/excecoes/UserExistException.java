package br.ufpb.dcx.project.excecoes;

public class UserExistException extends RuntimeException{
    public UserExistException(String message) {
        super(message);
    }
}
