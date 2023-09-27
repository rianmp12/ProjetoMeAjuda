package br.ufpb.dcx.project.servicos;


import br.ufpb.dcx.project.dto.ResponseLoginDTO;
import br.ufpb.dcx.project.dto.UserLoginDTO;
import br.ufpb.dcx.project.excecoes.LoginInvalidoException;
import br.ufpb.dcx.project.filtros.FiltroDeTokens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

@Service
public class ServiceAuthJWT {
    @Autowired
    private UserServices userServices;
    public static final Key TOKEN_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public ResponseLoginDTO autenticacao(UserLoginDTO usuario) {
        if (!userServices.validarUsuarioSenha(usuario)) {
            throw new LoginInvalidoException(
                    "Login falhou, O usuário não foi autenticado. A requisição de login foi processada com sucesso, mas as informações passadas não foram corretas para autenticar o usuário com sucesso.");
        }

        String token = gerarToken(usuario.getEmail());
        return new ResponseLoginDTO(token);
    }



    private String gerarToken(String email) {
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(email)
                .signWith(TOKEN_KEY, SignatureAlgorithm.HS512)
                .setExpiration(new Date(System.currentTimeMillis() + 3 * 60 * 1000))
                .compact();// 3 min
    }

    public String getSujeitoDoToken(String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new SecurityException("Token inexistente ou mal formatado!");
        }

        // Extraindo apenas o token do cabecalho.
        String token = authorizationHeader.substring(FiltroDeTokens.TOKEN_INDEX);

        String subject = null;
        try {
            JwtParser parser = Jwts.parserBuilder().setSigningKey(TOKEN_KEY).build();
            subject = parser.parseClaimsJws(token).getBody().getSubject();
        } catch (SignatureException e) {
            throw new SecurityException("Token invalido ou expirado!");
        }
        return subject;
    }
}
