package com.projectManagement.dto;

public class HandshakeDto extends UserDto {

  public String jwt;

  public HandshakeDto (String jwt, UserDto userDto) {
    super(userDto.getUserID(), userDto.getLastName(), userDto.getFirstName(), userDto.getPatronymic(), userDto.getBirthday(), userDto.getEmailAddress(),
      userDto.getPassword(), userDto.getMoney(), userDto.getUserRole(), userDto.getUserStatus(), userDto.getProjects());
    this.jwt = jwt;
  }

  public String getJwt() {
    return jwt;
  }

  public void setJwt(String jwt) {
    this.jwt = jwt;
  }
}
