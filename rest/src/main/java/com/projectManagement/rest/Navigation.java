package com.projectManagement.rest;

public interface Navigation {

  String LOAD = "/load";

  String PATH_AUTH_AUTHENTICATE = "/auth/authenticate";
  String PATH_USER_SIGN_UP = "/user/signUp";

  String PATH_USER_ALL_DRIVERS = "/user/allDrivers";
  String PATH_USER_NEW = "/user/new";
  String PATH_USER_EDIT_ME = "/user/editMe";
  String PATH_USER_GET_ME = "/user/getMe";
  String PATH_USER_ALL = "/user/all";
  String PATH_USER_GET_BY_ID = "/user/getById";
  String PATH_USER_GET_ADMIN = "/user/getAdmin";
  String PATH_USER_EDIT = "/user/edit";
  String PATH_USER_CHANGE_STATUS = "/user/changeStatus";
  String PATH_USER_DELETE = "/user/delete";

  String PATH_PROJECT_LOAD = "/project/load";
}
