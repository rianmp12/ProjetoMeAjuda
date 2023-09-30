package br.ufpb.dcx.project.exception;

public class LoginInvalidoException extends RuntimeException{
    public LoginInvalidoException(String message) {
        super(message);
    }
}
