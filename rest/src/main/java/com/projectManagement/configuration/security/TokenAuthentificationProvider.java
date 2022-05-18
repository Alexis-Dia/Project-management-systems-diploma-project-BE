package com.projectManagement.configuration.security;

import com.projectManagement.service.UserService;
import com.projectManagement.service.exceptions.UserNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Primary
public class TokenAuthentificationProvider implements AuthenticationProvider {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private TokenComponent tokenComponent;

    // FIXME: Try to change if-clause to lambda-style using ifPresent. I've noticed that it demands Java 9+ version of compiler
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

      String username = (String) authentication.getPrincipal();
      String rawPassword = (String) authentication.getCredentials();

      UserDetails userDetails = userDetailsService.loadUserByUsername(username);
      if (Objects.isNull(userDetails)) {
        throw new UserNotFoundException("Invalid username or password");
      }

      String encoodedPassword = userDetails.getPassword();

      boolean same = encoder.matches(rawPassword, encoodedPassword) ||
        rawPassword.equals(encoodedPassword);
/*
      if (!same) {
        throw new UserNotFoundException("Invalid username or password");
      }
*/

      String token = tokenComponent.generateToken(username);

      return new TokenAuthentication(userDetails, token);





//            Optional<UserDto> user = userService.findByLogin(authentication.getName());
//            String credentials = authentication.getCredentials().toString();
//
//            if (!user.isPresent()) {
//                throw new UserNotFoundException(MSG_ERR_USER_WASN_T_FOUND);
//            }
//
//            if (!bCryptPasswordEncoder.matches(credentials, user.get().getPassword())) {
//                throw new UserNotFoundException(MSG_ERR_INCORRECT_PASSORD);
//            }
//
//            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//            grantedAuthorities.add(new SimpleGrantedAuthority(user.get().getUserRole().getName()));
//
//        return new UsernamePasswordAuthenticationToken(user.get().getEmailAddress(), user.get().getPassword(),
//            grantedAuthorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
