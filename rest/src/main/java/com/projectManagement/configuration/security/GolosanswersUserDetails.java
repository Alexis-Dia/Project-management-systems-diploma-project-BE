package com.projectManagement.configuration.security;

import com.projectManagement.dto.UserDto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class GolosanswersUserDetails implements UserDetails {

  private final UserDto user;

  GolosanswersUserDetails(UserDto user) {

    this.user = user;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {

    return Collections.singletonList(new SimpleGrantedAuthority(user.getUserRole().name()));
  }

  @Override
  public String getPassword() {

    return user.getPassword();
  }

  @Override
  public String getUsername() {

    return user.getEmailAddress();
  }

  @Override
  public boolean isAccountNonExpired() {

    return true;
  }

  @Override
  public boolean isAccountNonLocked() {

    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {

    return true;
  }

  @Override
  public boolean isEnabled() {

    return true;
  }
}
