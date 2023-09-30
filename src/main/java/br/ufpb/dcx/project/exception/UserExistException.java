package br.ufpb.dcx.project.exception;

public class UserExistException extends RuntimeException{
    public UserExistException(String message) {
        super(message);
    }
}
