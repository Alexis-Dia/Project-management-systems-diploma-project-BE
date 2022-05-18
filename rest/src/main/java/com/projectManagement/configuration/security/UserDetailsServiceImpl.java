package com.projectManagement.configuration.security;

import com.projectManagement.dto.UserDto;
import com.projectManagement.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserService userService;

  @Autowired
  public UserDetailsServiceImpl(UserService userService) {

    this.userService = userService;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    try {

      Optional<UserDto> user = userService.findByLogin(username);
      return new GolosanswersUserDetails(user.get());

    } catch (Exception e) {

      throw new UsernameNotFoundException("Username not found", e);
    }
  }
}
