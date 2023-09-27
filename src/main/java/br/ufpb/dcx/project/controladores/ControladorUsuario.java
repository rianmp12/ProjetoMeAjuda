package br.ufpb.dcx.project.controladores;

import br.ufpb.dcx.project.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ControladorUsuario {

    @Autowired
    private UsuarioServico servicoUsuario;

    @PostMapping("api/user")
    public ResponseEntity<DTOUsuario> adicionaUsuario(@RequestBody DTOPostUsuario user){
        return ResponseEntity.status(HttpStatus.CREATED).body(servicoUsuario.adicionarUsuario(user));
    }

    @PutMapping("/auth/api/user/{email}")
    public ResponseEntity<DTOUsuario> atualizarUsuario(@RequestBody Usuario user, @PathVariable("email") String email, @RequestHeader("Authorization") String header){
        return ResponseEntity.ok(servicoUsuario.atualizarUsuario(user, email, header));
    }

    @GetMapping("/auth/api/user/{email}")
    public ResponseEntity<DTOUsuario> recuperarUsuario(@PathVariable("email") String email, @RequestHeader("Authorization") String header){
        return ResponseEntity.ok(servicoUsuario.recuperarUsuario(email, header));
    }

    @DeleteMapping("/auth/api/user/{email}")
    public ResponseEntity<DTOUsuario> removerUsuario(@PathVariable("email") String email, @RequestHeader("Authorization") String header){
        return ResponseEntity.ok(servicoUsuario.removerUsuario(email, header));
    }


}
