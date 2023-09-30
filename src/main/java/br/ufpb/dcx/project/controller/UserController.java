package br.ufpb.dcx.project.controller;

import br.ufpb.dcx.project.dto.PostUserDTO;
import br.ufpb.dcx.project.dto.UserDTO;
import br.ufpb.dcx.project.model.User;
import br.ufpb.dcx.project.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserServices userServices;

    @PostMapping("api/user")
    public ResponseEntity<UserDTO> addUser(@RequestBody PostUserDTO user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userServices.addUser(user));
    }

    @PutMapping("/auth/api/user/{email}")
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