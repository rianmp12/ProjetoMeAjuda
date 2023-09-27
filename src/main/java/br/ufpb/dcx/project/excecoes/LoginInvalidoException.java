package br.ufpb.dcx.project.excecoes;

public class LoginInvalidoException extends RuntimeException{
    public LoginInvalidoException(String message) {
        super(message);
    }
}
