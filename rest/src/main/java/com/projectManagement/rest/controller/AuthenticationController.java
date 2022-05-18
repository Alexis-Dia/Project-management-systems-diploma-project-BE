package com.projectManagement.rest.controller;

import static com.projectManagement.consts.Common.MSG_ERR_INCORRECT_PASSORD;
import static com.projectManagement.consts.Common.MSG_ERR_USER_ALREADY_EXISTS;
import static com.projectManagement.consts.Common.MSG_ERR_USER_WASN_T_FOUND;

import com.projectManagement.configuration.security.TokenAuthentication;
import com.projectManagement.dto.HandshakeDto;
import com.projectManagement.dto.UserDto;
import com.projectManagement.service.UserService;
import com.projectManagement.service.exceptions.UserAlreadyExistsException;
import com.projectManagement.service.exceptions.UserNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/authenticate")
    public HandshakeDto authenticate(@RequestParam("emailAddress") String emailAddress, @RequestParam("password") String password) {

        TokenAuthentication authentication = (TokenAuthentication)
          authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(emailAddress, password));

        Optional<UserDto> userByLogin = userService.findByLogin(emailAddress);

        if (!userByLogin.isPresent()) {
            throw new UserNotFoundException(MSG_ERR_USER_WASN_T_FOUND);
        }

        //return new HandshakeDto(authentication.getToken(), user.getRole().access(), user.getUsername(), user.getLanguage());
        HandshakeDto handshakeDto = new HandshakeDto(authentication.getToken(), userByLogin.get());
        return handshakeDto;

//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        String decodedPassword = userByLogin.get().getPassword();
//        if (!bCryptPasswordEncoder.matches(password, decodedPassword)) {
//            throw new UserNotFoundException(MSG_ERR_INCORRECT_PASSORD);
//        }
//
////        TokenAuthentication authentication = (TokenAuthentication) authenticationManager.
////          .authenticate(new UsernamePasswordAuthenticationToken(emailAddress, password));
//
//        authenticationManager
//        userByLogin.get().setPassword(password);
//        return userByLogin.get();
    }

    @PostMapping("/checkUser")
    public void checkUser(@RequestBody UserDto userDto) {

        Optional<UserDto> userByLogin = userService.findByLogin(userDto.getEmailAddress());

        if (userByLogin.isPresent()) {
            throw new UserAlreadyExistsException(MSG_ERR_USER_ALREADY_EXISTS);
        }

    }
}
