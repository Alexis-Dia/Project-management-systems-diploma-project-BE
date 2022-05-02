package com.projectManagement.rest.controller;

import static com.projectManagement.consts.Common.MSG_ERR_INCORRECT_PASSORD;
import static com.projectManagement.consts.Common.MSG_ERR_USER_ALREADY_EXISTS;
import static com.projectManagement.consts.Common.MSG_ERR_USER_WASN_T_FOUND;

import com.projectManagement.dto.UserDto;
import com.projectManagement.service.UserService;
import com.projectManagement.service.exceptions.UserAlreadyExistsException;
import com.projectManagement.service.exceptions.UserNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @GetMapping("/authenticate")
    public UserDto authenticate(@RequestParam("emailAddress") String emailAddress, @RequestParam("password") String password) {

        Optional<UserDto> userByLogin = userService.findByLogin(emailAddress);

        if (!userByLogin.isPresent()) {
            throw new UserNotFoundException(MSG_ERR_USER_WASN_T_FOUND);
        }

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String decodedPassword = userByLogin.get().getPassword();
        if (!bCryptPasswordEncoder.matches(password, decodedPassword)) {
            throw new UserNotFoundException(MSG_ERR_INCORRECT_PASSORD);
        }

        userByLogin.get().setPassword(password);
        return userByLogin.get();
    }

    @PostMapping("/checkUser")
    public void checkUser(@RequestBody UserDto userDto) {

        Optional<UserDto> userByLogin = userService.findByLogin(userDto.getEmailAddress());

        if (userByLogin.isPresent()) {
            throw new UserAlreadyExistsException(MSG_ERR_USER_ALREADY_EXISTS);
        }

    }
}
