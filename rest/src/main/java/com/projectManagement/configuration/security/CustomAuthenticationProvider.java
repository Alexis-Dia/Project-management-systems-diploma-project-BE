package com.projectManagement.configuration.security;

import com.projectManagement.dto.UserDto;
import com.projectManagement.service.UserService;
import com.projectManagement.service.exceptions.UserNotFoundException;

import static com.projectManagement.consts.Common.MSG_ERR_INCORRECT_PASSORD;
import static com.projectManagement.consts.Common.MSG_ERR_USER_WASN_T_FOUND;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserService userService;

    // FIXME: Try to change if-clause to lambda-style using ifPresent. I've noticed that it demands Java 9+ version of compiler
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

            Optional<UserDto> user = userService.findByLogin(authentication.getName());
            String credentials = authentication.getCredentials().toString();

            if (!user.isPresent()) {
                throw new UserNotFoundException(MSG_ERR_USER_WASN_T_FOUND);
            }

            if (!bCryptPasswordEncoder.matches(credentials, user.get().getPassword())) {
                throw new UserNotFoundException(MSG_ERR_INCORRECT_PASSORD);
            }

            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            grantedAuthorities.add(new SimpleGrantedAuthority(user.get().getUserRole().getName()));

        return new UsernamePasswordAuthenticationToken(user.get().getEmailAddress(), user.get().getPassword(),
            grantedAuthorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
