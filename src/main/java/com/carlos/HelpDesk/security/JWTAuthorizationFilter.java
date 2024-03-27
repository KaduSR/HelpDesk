package com.carlos.HelpDesk.security;

import com.carlos.HelpDesk.domain.dtos.CredenciaisDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JWTAuthorizationFilter
  extends UsernamePasswordAuthenticationFilter {

  private AuthenticationManager authenticationManager;
  private JWTUtil jwtUtil;

  // Construtor da classe que recebe o gerenciador de autenticação e o utilitário JWT como parâmetros
  public JWTAuthorizationFilter(
    AuthenticationManager authenticationManager,
    JWTUtil jwtUtil
  ) {
    this.authenticationManager = authenticationManager;
    this.jwtUtil = jwtUtil;
  }

  // Método chamado para tentar autenticar o usuário com base nos dados fornecidos
  @Override
  public Authentication attemptAuthentication(
    HttpServletRequest request,
    HttpServletResponse response
  ) throws AuthenticationException {
    try {
      CredenciaisDTO creds = new ObjectMapper()
        .readValue(request.getInputStream(), CredenciaisDTO.class);
      UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
        creds.getEmail(),
        creds.getSenha(),
        new ArrayList<>()
      );
      Authentication authentication = authenticationManager.authenticate(
        authenticationToken
      );
      return authentication;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  // Método chamado quando a autenticação é bem-sucedida
  @Override
  protected void successfulAuthentication(
    HttpServletRequest request,
    HttpServletResponse response,
    FilterChain chain,
    Authentication authResult
  ) throws IOException, ServletException {
    String username = (((UserSS) authResult.getPrincipal()).getUsername());
    String token = jwtUtil.generateToken(username);
    response.setHeader("acess-control-expose-headers", "Authorization");
    response.setHeader("Authorization", "Bearer " + token);
  }

  // Método chamado quando a autenticação falha
  @Override
  protected void unsuccessfulAuthentication(
    HttpServletRequest request,
    HttpServletResponse response,
    AuthenticationException failed
  ) throws IOException, ServletException {
    response.setStatus(401);
    response.setContentType("application/json");
    response.getWriter().append(json());
  }

  private CharSequence json() {
    long date = new Date().getTime();
    return (
      "{\"timestamp\":\"" +
      date +
      "\"," +
      "\"status\":401," +
      "\"error\":\"Não autorizado\", \"message\":\"Email ou senha inválidos\"}"
    );
  }
}
