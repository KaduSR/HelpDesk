package com.carlos.HelpDesk.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {

    // Injeta o valor da propriedade 'jwt.expiration' definida no arquivo de propriedades
    @Value("${jwt.expiration}")
    private Long expiration;

    // Injeta o valor da propriedade 'jwt.secret' definida no arquivo de propriedades
    @Value("${jwt.secret}")
    private String secret;

    // Método para gerar um token JWT com base no e-mail fornecido
    public String generateToken(String email) {
       return Jwts.builder()
               .setSubject(email) // Define o assunto do token como o e-mail fornecido
               .setExpiration(new Date(System.currentTimeMillis() + expiration)) // Define a data de expiração do token
               .signWith(SignatureAlgorithm.HS512, secret.getBytes()) // Assina o token com o algoritmo HS512 e o segredo fornecido
               .compact(); // Compacta o token em uma String
    }
    
}
