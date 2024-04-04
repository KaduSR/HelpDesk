package com.carlos.HelpDesk.config;

import com.carlos.HelpDesk.security.JWTAuthenticationFilter;
import com.carlos.HelpDesk.security.JWTAuthorizationFilter;
import com.carlos.HelpDesk.security.JWTUtil;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

<<<<<<< HEAD
  // Define os endpoints públicos que não exigem autenticação
  private static final String[] PUBLIC_MATCHERS = {
    "/**",
    "/h2/**",
    "/login/**",
  };
=======
    // Define os endpoints públicos que não exigem autenticação
    private static final String[] PUBLIC_MATCHERS = { "/h2-console/**", "/login/" };
>>>>>>> 0bce57c8c3e05dbb2be28c550c3771f208965544

  @Autowired
  private Environment env;

  @Autowired
  private JWTUtil jwtUtil;

  @Autowired
  private UserDetailsService userDetailsService;

  // Configura o gerenciador de autenticação para ser injetado em outras partes do aplicativo
  @Bean
  public AuthenticationManager authenticationManager(
    AuthenticationConfiguration authConfiguration
  ) throws Exception {
    return authConfiguration.getAuthenticationManager();
  }

  // Configura o filtro de segurança que processa as requisições HTTP
  @Bean
  public SecurityFilterChain securityFilterChain(
    HttpSecurity http,
    AuthenticationConfiguration authConfiguration
  ) throws Exception {
    // Configurações específicas para o ambiente de teste
    if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
      http.headers(headers -> headers.frameOptions(options -> options.disable())
      );
    }
      http.cors(withDefaults());
    http.addFilter(
      new JWTAuthenticationFilter(
        authConfiguration.getAuthenticationManager(),
        jwtUtil
      )
    );
    http.addFilter(
      new JWTAuthorizationFilter(
        authConfiguration.getAuthenticationManager(),
        jwtUtil,
        userDetailsService
      )
    );

    return http
            .csrf(csrf -> csrf
                    .disable())
            .sessionManagement(management -> management
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(requests -> requests
                    .requestMatchers(PUBLIC_MATCHERS)
                    .permitAll()
                    .anyRequest()
                    .authenticated())
            .build();
  }

  // Bean para configurar o codificador de senhas BCrypt
  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    // Vincula a estratégia de autenticação JWT à configuração do Spring Security
    auth
      .userDetailsService(userDetailsService)
      .passwordEncoder(bCryptPasswordEncoder());
  }

  // Configuração CORS para permitir requisições de origens diferentes
  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration()
      .applyPermitDefaultValues();
    configuration.addAllowedOrigin("*");
    configuration.addAllowedHeader("*");
    configuration.setAllowedMethods(
      Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS")
    );
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }
}
