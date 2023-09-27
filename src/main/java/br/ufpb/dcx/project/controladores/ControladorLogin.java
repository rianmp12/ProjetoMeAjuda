package br.ufpb.dcx.project.controladores;

import br.ufpb.dcx.project.servicos.AutenticacaoServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControladorLogin {

    @Autowired
    private AutenticacaoServico servicoAutenticacao;

    @PostMapping("auth/login")
    public ResponseEntity<DTOLoginResposta> login(@RequestBody DTOLoginUsuario usuarioLogin){
        return new ResponseEntity<DTOLoginResposta>(servicoAutenticacao.autenticacao(usuarioLogin), HttpStatus.OK);
    }
}
