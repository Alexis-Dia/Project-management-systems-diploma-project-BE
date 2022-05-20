package com.projectManagement.rest;

public interface Navigation {

  String USER = "/user";
  String PROJECT = "/project";
  String TASK = "/task";
  String REPORT = "/report";
  String LOAD = "/load";
  String LOAD_ALL_MINE_TASKS_IN_PROGRESS = "/loadAllMineInProgress";
  String LOAD_ALL_MINE_NEW_TASKS_NEW = "/loadAllMineNew";
  String LOAD_ALL_MINE_PROJECTS = "/loadAllMine";
  String ADD_USER = "/addUser";
  String REMOVE_USER = "/removeUser";
  String CREATE_REPORT = "/createReport/{taskId}";
  String CREATE_PROJECT = "/createProject";
  String CREATE_TASK = "/createTask/{projectId}";
  String CHANGE_TASK_STATUS = "/changeTaskStatus";

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

  String GET_BY_TASK_ID = "/getByTaskId";

  String PATH_PROJECT_LOAD = PROJECT + LOAD;
  String PATH_PROJECT_CREATE = PROJECT + CREATE_PROJECT;

  String PATH_TASK_LOAD = TASK + LOAD;
  String PATH_TASK_CREATE = TASK + CREATE_REPORT;
  String PATH_TASK_LOAD_ALL_MINE_IN_PROGRESS = TASK + LOAD_ALL_MINE_TASKS_IN_PROGRESS;
  String PATH_TASK_LOAD_ALL_MINE_NEW = TASK + LOAD_ALL_MINE_NEW_TASKS_NEW;
  String PATH_PROJECT_LOAD_ALL_MINE = PROJECT + LOAD_ALL_MINE_PROJECTS;
  String PATH_METHOD_GET_ADD_USER_TO_THE_PROJECT = PROJECT + ADD_USER;
  String PATH_METHOD_GET_REMOVE_USER_FROM_THE_PROJECT = PROJECT + REMOVE_USER;
  String PATH_REPORT_GET_BY_TASK_ID = REPORT + GET_BY_TASK_ID;
  String PATH_CHANGE_TASK_STATUS = TASK + CHANGE_TASK_STATUS;

  String PATH_REPORT_LOAD = REPORT + LOAD;
  String PATH_REPORT_CREATE = REPORT + CREATE_REPORT;
}
