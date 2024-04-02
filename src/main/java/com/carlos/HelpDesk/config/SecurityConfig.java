package com.carlos.HelpDesk.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.carlos.HelpDesk.security.JWTAuthenticationFilter;
import com.carlos.HelpDesk.security.JWTAuthorizationFilter;
import com.carlos.HelpDesk.security.JWTUtil;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Define os endpoints públicos que não exigem autenticação
    private static final String[] PUBLIC_MATCHERS = { "/h2-console/**" };

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
            http.headers(headers -> headers.frameOptions(options -> options.disable()));
        }

        // Adiciona os filtros de autenticação JWT e autorização JWT
        http.addFilter(
                new JWTAuthenticationFilter(
                        authenticationManager(authConfiguration),
                        jwtUtil
                )
        );

        http.addFilter(
                new JWTAuthorizationFilter(
                        authenticationManager(authConfiguration),
                        jwtUtil,
                        userDetailsService
                )
        );

        // Configurações de segurança HTTP
        return http
                .csrf(csrf -> csrf.disable()) // Desativa a proteção CSRF
                .sessionManagement(management ->
                        management.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                ) // Define a política de gerenciamento de sessão como sem estado
                .authorizeHttpRequests(requests ->
                        requests
                                .requestMatchers(PUBLIC_MATCHERS)
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                ) // Define a autorização de requisições
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
        configuration.setAllowedMethods(
                Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS")
        );
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
