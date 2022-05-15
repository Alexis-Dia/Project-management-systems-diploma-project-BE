package com.projectManagement.rest;

public interface Navigation {

  String USER = "/user";
  String PROJECT = "/project";
  String TASK = "/task";
  String REPORT = "/rept";
  String LOAD = "/load";
  String LOAD_ALL_MINE_TASKS = "/loadAllMine";
  String LOAD_ALL_MINE_PROJECTS = "/loadAllMine";
  String CREATE = "/create";

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

  String PATH_PROJECT_LOAD = PROJECT + LOAD;
  String PATH_PROJECT_CREATE = PROJECT + CREATE;

  String PATH_TASK_LOAD = TASK + LOAD;
  String PATH_TASK_CREATE = TASK + CREATE;
  String PATH_TASK_LOAD_ALL_MINE = TASK + LOAD_ALL_MINE_TASKS;
  String PATH_PROJECT_LOAD_ALL_MINE = PROJECT + LOAD_ALL_MINE_PROJECTS;

  String PATH_REPORT_LOAD = REPORT + LOAD;
  String PATH_REPORT_CREATE = REPORT + CREATE;
}
