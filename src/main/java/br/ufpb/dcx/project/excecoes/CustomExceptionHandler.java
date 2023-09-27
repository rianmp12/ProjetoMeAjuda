package br.ufpb.dcx.project.excecoes;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.net.URISyntaxException;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoTagException.class)
    public ResponseEntity<Object> handleNoTagException(){
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(DisciplinaNotFoudException.class)
    public ResponseEntity<ProblemDetails> handleDisciplinaNotFoud(DisciplinaNotFoudException ex) throws URISyntaxException {
        ProblemDetails problemDetails = new ProblemDetails();
        problemDetails.setType(new URI("about:blank"));
        problemDetails.setTitle("Disciplina Not Found");
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

    @ExceptionHandler(LoginInvalidoException.class)
    public ResponseEntity<ProblemDetails> handleLoginInvalid(LoginInvalidoException ex) throws URISyntaxException {
        ProblemDetails problemDetails = new ProblemDetails();
        problemDetails.setType(new URI("about:blank"));
        problemDetails.setTitle("Login Inválido");
        problemDetails.setDetail(ex.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(problemDetails);
    }

    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<ProblemDetails> handleLoginInvalid(CommentNotFoundException ex) throws URISyntaxException {
        ProblemDetails problemDetails = new ProblemDetails();
        problemDetails.setType(new URI("about:blank"));
        problemDetails.setTitle("Comentário não encontrado");
        problemDetails.setDetail(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problemDetails);
    }

    @ExceptionHandler(UserExistException.class)
    public ResponseEntity<ProblemDetails> handleLoginInvalid(UserExistException ex) throws URISyntaxException {
        ProblemDetails problemDetails = new ProblemDetails();
        problemDetails.setType(new URI("about:blank"));
        problemDetails.setTitle("Usuário já existe com esse email");
        problemDetails.setDetail(ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(problemDetails);
    }


}
