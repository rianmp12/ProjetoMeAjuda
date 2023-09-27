package br.ufpb.dcx.project.excecoes;

import lombok.Getter;
import lombok.Setter;

import java.net.URI;

@Setter
@Getter
public class ProblemDetails {

    private URI type;
    private String title;
    private String detail;

}
