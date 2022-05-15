package com.projectManagement.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.projectManagement.entity.UserRole;
import com.projectManagement.entity.UserStatus;

import java.util.Date;
import java.util.Set;

public class UserDto {

    private Long userID;

    private String lastName;

    private String firstName;

    private String patronymic;

    private Date birthday;

    private String emailAddress;

    private String password;

    private Float money;

    private UserRole userRole;

    private UserStatus userStatus;

    private Set<ProjectDto> projects;

    public UserDto() {
    }

/*    @JsonCreator
    public UserDto(@JsonProperty Integer userID) {
        this.userID = userID;
    }*/

//    public UserDto(Long userID, String lastName, String firstName, String patronymic, Date birthday, String emailAddress,
//                   String password, Float money, UserRole userRole, UserStatus userStatus) {
//        this.userID = userID;
//        this.lastName = lastName;
//        this.firstName = firstName;
//        this.patronymic = patronymic;
//        this.birthday = birthday;
//        this.emailAddress = emailAddress;
//        this.password = password;
//        this.money = money;
//        this.userRole = userRole;
//        this.userStatus = userStatus;
//    }

    @JsonCreator
    public UserDto(@JsonProperty("id") Long userID,
                   @JsonProperty("lastName") String lastName,
                   @JsonProperty("firstName") String firstName,
                   @JsonProperty("patronymic") String patronymic,
                   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date birthday,
                   @JsonProperty("emailAddress") String emailAddress,
                   @JsonProperty("password") String password,
                   @JsonProperty("money") Float money,
                   @JsonProperty("userRole") UserRole userRole,
                   @JsonProperty("userStatus") UserStatus userStatus,
                   @JsonProperty("projects") Set<ProjectDto> projects) {
        this.userID = userID;
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.emailAddress = emailAddress;
        this.password = password;
        this.money = money;
        this.userRole = userRole;
        this.userStatus = userStatus;
        this.projects = projects;
    }

    public UserDto(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public void setProjects(Set<ProjectDto> projects) {
        this.projects = projects;
    }

    public Set<ProjectDto> getProjects() {
        return projects;
    }

    @Override
    public String toString() {
        return "UserDto{" +
          "userID=" + userID +
          ", lastName='" + lastName + '\'' +
          ", firstName='" + firstName + '\'' +
          ", patronymic='" + patronymic + '\'' +
          ", birthday=" + birthday +
          ", emailAddress='" + emailAddress + '\'' +
          ", password='" + password + '\'' +
          ", money=" + money +
          ", userRole=" + userRole +
          ", userStatus=" + userStatus +
          ", projects=" + projects +
          '}';
    }
}
