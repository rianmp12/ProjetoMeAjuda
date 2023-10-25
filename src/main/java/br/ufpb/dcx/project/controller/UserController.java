package br.ufpb.dcx.project.controller;

import br.ufpb.dcx.project.dto.PostUserDTO;
import br.ufpb.dcx.project.dto.ResponseLoginDTO;
import br.ufpb.dcx.project.dto.UserDTO;
import br.ufpb.dcx.project.dto.UserLoginDTO;
import br.ufpb.dcx.project.model.User;
import br.ufpb.dcx.project.service.ServiceAuthJWT;
import br.ufpb.dcx.project.service.UserServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserServices userServices;
    @Autowired
    private ServiceAuthJWT serviceAuthJWT;

    @PostMapping("api/user")
    public ResponseEntity<UserDTO> addUser(@RequestBody PostUserDTO user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userServices.addUser(user));
    }
    @PostMapping("api/login")
    @ResponseStatus(code = HttpStatus.OK)
     @Operation(summary = "Autentica o usuário devidamente cadastrado.",
            description = "É preciso informar o e-mail de um usuário cadastrado no sistema e a senha " +
                    "correspondente para que um usuário seja devidamente autenticado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário logado/autenticado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Login inválido, usuário ou senha são inválidos.")
    })
    
    public ResponseLoginDTO auth(@RequestBody UserLoginDTO user){
        return serviceAuthJWT.authentication(user);
    }

    @PutMapping("/auth/api/user/{email}")
    @Operation(summary = "Atualiza o usuário devidamente cadastrado.",
            description = "É possivel modificar as informações de usuário caso ele esteja logado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado."),
            @ApiResponse(responseCode = "403", description = "Usuário não autorizado.")
    })
    public ResponseEntity<UserDTO> updateUser(@RequestBody User user, @PathVariable("email") String email, @RequestHeader("Authorization") String header){
        return ResponseEntity.ok(userServices.updateUser(user, email, header));
    }

    @GetMapping("/auth/api/user/{email}")
    public ResponseEntity<UserDTO> recoveryUser(@PathVariable("email") String email, @RequestHeader("Authorization") String header){
        return ResponseEntity.ok(userServices.recoveryUser(email, header));
    }

    @DeleteMapping("/auth/api/user/{email}")
    public ResponseEntity<UserDTO> removerUsuario(@PathVariable("email") String email, @RequestHeader("Authorization") String header){
        return ResponseEntity.ok(userServices.removeUser(email, header));
    }
}