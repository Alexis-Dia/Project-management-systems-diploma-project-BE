package com.projectManagement.configuration.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

public class TokenAuthentication extends UsernamePasswordAuthenticationToken {

  private final String token;

  TokenAuthentication(UserDetails details, String token) {

    super(details.getUsername(), details.getPassword(), details.getAuthorities());
    this.token = token;
  }

  public String getToken() {

    return token;
  }
}