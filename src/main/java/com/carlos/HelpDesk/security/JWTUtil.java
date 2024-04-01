package com.carlos.HelpDesk.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
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

    public boolean tokenValido(String token) {
        Claims claims = getClaims(token);
        if (claims != null){
            String username = claims.getSubject();
            Date expirationDate = claims.getExpiration();
            Date now = new Date(System.currentTimeMillis());

            if(username != null && expirationDate != null && now.before(expirationDate)) {
                return true;
            }
        }
        return false; // Retorna false se alguma das condições não for satisfeita
    }

    private Claims getClaims(String token) {
       try {
        return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
       } catch (Exception e) {
            return null;
       }
    }

    public String getUsername(String token) {
        Claims claims = getClaims(token);
        if (claims != null) {
            return claims.getSubject();
        }
        return null;
    }
    
}
