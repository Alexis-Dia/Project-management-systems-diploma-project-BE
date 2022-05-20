package com.projectManagement.service;

import com.projectManagement.dto.ProjectDto;
import com.projectManagement.dto.TaskDto;

import java.util.List;

public interface TaskService {

  List<TaskDto> findAll();

  List<TaskDto> findAllMineCurrentTasksInProgress(Long userId);

  List<TaskDto> findAllMineNewTasks(Long userId);

  void createTask(Long projectId, TaskDto task);

  TaskDto changeTaskStatus(Long taskId, String status, String email);
}
