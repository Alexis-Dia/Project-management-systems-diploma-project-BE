package com.projectManagement.service;

import com.projectManagement.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

  List<UserDto> findAll();

  //TODO: FIX IT
  List<UserDto> findAllDrivers();

  UserDto getDriverById(Long userId);

  UserDto getMe(Long userId, String authenticationName);

  UserDto getAdmin();

  Optional<UserDto> findByLogin(String login);

  UserDto editUser(UserDto userDto);

  UserDto editMe(UserDto userDto, String authenticationName);

  UserDto changeUserStatus(Long userId, String userStatus);

  List<UserDto> deleteUser(Long userId);

  void createNewUser(UserDto userDto);

  void transferMoney(Long userId, Float money) throws Exception;

  void informBankManager() throws Exception;

  void withdraw(Long fromUser, Float amount) throws Exception;

  void deposit(Long toUser, Float amount) throws Exception;
}
