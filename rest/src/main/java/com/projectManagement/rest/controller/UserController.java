package com.projectManagement.rest.controller;

import com.projectManagement.dto.UserDto;
import com.projectManagement.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<UserDto> getAllUsers() {

        final List<UserDto> allUsers = userService.findAll();

        return allUsers;
    }

    @GetMapping("/allDrivers")
    public List<UserDto> findAllDrivers() {

        final List<UserDto> allDrivers = userService.findAllDrivers();

        return allDrivers;
    }

    @GetMapping("/getById")
    public UserDto getDriverById(@RequestParam("id") Long userId) {

        final UserDto userDto = userService.getDriverById(userId);

        return userDto;
    }

    @GetMapping("/getMe")
    public UserDto getMe(@RequestParam("id") Long userId) {

        final String authenticationName = SecurityContextHolder.getContext().getAuthentication().getName();

        final UserDto userDto = userService.getMe(userId, authenticationName);

        return userDto;
    }

    @GetMapping("/getAdmin")
    public UserDto geAdmin() {

        final UserDto userDto = userService.getAdmin();

        return userDto;
    }

    @PutMapping("/edit")
    public UserDto editUser(@RequestBody UserDto userDto) {

        final UserDto user = userService.editUser(userDto);

        return user;
    }

    @PutMapping("/editMe")
    public UserDto editCurrentUser(@RequestBody UserDto userDto) {

        final String authenticationName = SecurityContextHolder.getContext().getAuthentication().getName();

        final UserDto user = userService.editMe(userDto, authenticationName);

        return user;
    }

    @PutMapping("/changeStatus")
    public UserDto changeUserStatus(@RequestBody UserDto userDto) {

        final UserDto user = userService.changeUserStatus(userDto.getUserID(), userDto.getUserStatus().name());

        return user;
    }

    @DeleteMapping("/delete")
    public List<UserDto> deleteUser(@RequestParam("id") Long userId) {

        final List<UserDto> allUsers = userService.deleteUser(userId);

        return allUsers;
    }

    @PostMapping("/signUp")
    public void createNew(@RequestBody UserDto userDto) {

        //TODO - Try to create it using autowiring
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        userService.createNewUser(userDto);

    }

}
