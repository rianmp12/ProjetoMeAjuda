package br.ufpb.dcx.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.net.URISyntaxException;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(CampaignNotFoundException.class)
    public ResponseEntity<ProblemDetails> handleDisciplinaNotFoud(CampaignNotFoundException ex) throws URISyntaxException {
        ProblemDetails problemDetails = new ProblemDetails();
        problemDetails.setType(new URI("about:blank"));
        problemDetails.setTitle("Campanha Not_Found");
        problemDetails.setDetail(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problemDetails);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ProblemDetails> handleUserNotFoud(UserNotFoundException ex) throws URISyntaxException {
        ProblemDetails problemDetails = new ProblemDetails();
        problemDetails.setType(new URI("about:blank"));
        problemDetails.setTitle("User Not Found");
        problemDetails.setDetail(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problemDetails);
    }

    @ExceptionHandler(UnauthorizedOperationException.class)
    public ResponseEntity<ProblemDetails> handleUnauthorizedOperation(UnauthorizedOperationException ex) throws URISyntaxException {
        ProblemDetails problemDetails = new ProblemDetails();
        problemDetails.setType(new URI("about:blank"));
        problemDetails.setTitle("Unauthorized Operation");
        problemDetails.setDetail(ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(problemDetails);
    }

    @ExceptionHandler(CampaignNotCloseException.class)
    public ResponseEntity<ProblemDetails> handleUnauthorizedOperation(CampaignNotCloseException ex) throws URISyntaxException {
        ProblemDetails problemDetails = new ProblemDetails();
        problemDetails.setType(new URI("about:blank"));
        problemDetails.setTitle("Campanha não pode ser expirada");
        problemDetails.setDetail(ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(problemDetails);
    }

    @ExceptionHandler(CampaignExpiredException.class)
    public ResponseEntity<ProblemDetails> handleUnauthorizedOperation(CampaignExpiredException ex) throws URISyntaxException {
        ProblemDetails problemDetails = new ProblemDetails();
        problemDetails.setType(new URI("about:blank"));
        problemDetails.setTitle("Campanha expirada");
        problemDetails.setDetail(ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(problemDetails);
    }

    @ExceptionHandler(LoginInvalidoException.class)
    public ResponseEntity<ProblemDetails> handleLoginInvalid(LoginInvalidoException ex) throws URISyntaxException {
        ProblemDetails problemDetails = new ProblemDetails();
        problemDetails.setType(new URI("about:blank"));
        problemDetails.setTitle("Login Inválido");
        problemDetails.setDetail(ex.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(problemDetails);
    }

    @ExceptionHandler(InvalidDonateException.class)
    public ResponseEntity<ProblemDetails> handleLoginInvalid(InvalidDonateException ex) throws URISyntaxException {
        ProblemDetails problemDetails = new ProblemDetails();
        problemDetails.setType(new URI("about:blank"));
        problemDetails.setTitle("Donate Inválido");
        problemDetails.setDetail(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetails);
    }

    @ExceptionHandler(UserExistException.class)
    public ResponseEntity<ProblemDetails> handleLoginInvalid(UserExistException ex) throws URISyntaxException {
        ProblemDetails problemDetails = new ProblemDetails();
        problemDetails.setType(new URI("about:blank"));
        problemDetails.setTitle("Usuário já existe com esse email");
        problemDetails.setDetail(ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(problemDetails);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetails> handleLoginInvalid(MethodArgumentNotValidException ex) throws URISyntaxException {
        String fieldName = ex.getBindingResult().getFieldError().getField();
        ProblemDetails problemDetails = new ProblemDetails();
        problemDetails.setType(new URI("about:blank"));
        problemDetails.setTitle("Erro de validação");
        problemDetails.setDetail(ex.getMessage());
        problemDetails.setField(fieldName);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetails);
    }


}
