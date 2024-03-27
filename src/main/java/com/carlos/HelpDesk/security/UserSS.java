package com.carlos.HelpDesk.security;

import com.carlos.HelpDesk.domain.enums.Perfil;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserSS implements UserDetails {

  private static final long serialVersionUID = 1L;

  private Integer id;
  private String email;
  private String senha;
  private Collection<? extends GrantedAuthority> authorities;

  public UserSS(Integer id, String email, String senha, Set<Perfil> perfils) {
    this.id = id;
    this.email = email;
    this.senha = senha;
    this.authorities =
      perfils
        .stream()
        .map(x -> new SimpleGrantedAuthority(x.getDescricao()))
        .collect(Collectors.toSet());
  }

  public Integer getId() {
    return id;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return senha;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true; //A conta não expira
  }

  @Override
  public boolean isAccountNonLocked() {
    return true; //Não está bloqueada a conta
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true; //As credenciais não expiram
  }

  @Override
  public boolean isEnabled() {
    return true; //O usuário pode logar no sistema
  }
}
