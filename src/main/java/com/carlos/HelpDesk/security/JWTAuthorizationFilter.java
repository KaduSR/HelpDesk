package com.carlos.HelpDesk.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

  private JWTUtil jwtUtil;
  private UserDetailsService userDetailsService;

  public JWTAuthorizationFilter(
    AuthenticationManager authenticationManager,
    JWTUtil jwtUtil,
    UserDetailsService userDetailsService
  ) {
    super(authenticationManager);
    this.jwtUtil = jwtUtil;
    this.userDetailsService = userDetailsService;
  }

  @Override
  protected void doFilterInternal(
    HttpServletRequest request,
    HttpServletResponse response,
    FilterChain chain
  ) throws IOException, ServletException {
    // Obtém o cabeçalho Authorization do request
    String header = request.getHeader("Authorization");
    if (header != null && header.startsWith("Bearer")) {
      // Remove o prefixo "Bearer " para obter apenas o token JWT
      String token = header.substring(7);
      // Obtém a autenticação a partir do token JWT
      UsernamePasswordAuthenticationToken authToken = getAuthentication(token);
      if (authToken != null) {
        // Define a autenticação no contexto de segurança
        SecurityContextHolder.getContext().setAuthentication(authToken);
      }
    }
    // Continua a cadeia de filtros
    chain.doFilter(request, response);
  }

  private UsernamePasswordAuthenticationToken getAuthentication(String token) {
    // Verifica se o token JWT é válido
    if (jwtUtil.tokenValido(token)) {
      // Obtém o nome de usuário a partir do token
      String username = jwtUtil.getUsername(token);
      // Obtém os detalhes do usuário com base no nome de usuário
      UserDetails details = userDetailsService.loadUserByUsername(username);
      // Retorna uma instância de autenticação com o nome de usuário, nenhuma senha e as autorizações do usuário
      return new UsernamePasswordAuthenticationToken(
        details.getUsername(),
        "null",
        details.getAuthorities()
      );
    }
    return null;
  }
}
