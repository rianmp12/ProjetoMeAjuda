package br.ufpb.dcx.project.exception;

public class UnauthorizedOperationException extends RuntimeException{
    public UnauthorizedOperationException(String message) {
        super(message);
    }
}
