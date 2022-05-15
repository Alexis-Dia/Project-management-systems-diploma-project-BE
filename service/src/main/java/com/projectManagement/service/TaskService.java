package com.projectManagement.service;

import com.projectManagement.dto.ProjectDto;
import com.projectManagement.dto.TaskDto;

import java.util.List;

public interface TaskService {

  List<TaskDto> findAll();

  List<TaskDto> findAllMineTasks(Long userId);

  void createTask(TaskDto task);
}
