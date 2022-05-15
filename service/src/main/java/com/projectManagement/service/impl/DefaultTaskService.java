package com.projectManagement.service.impl;

import com.projectManagement.dto.ProjectDto;
import com.projectManagement.dto.TaskDto;
import com.projectManagement.entity.ProjectEntity;
import com.projectManagement.entity.TaskEntity;
import com.projectManagement.repository.ProjectRepository;
import com.projectManagement.repository.TaskRepository;
import com.projectManagement.repository.UserRepository;
import com.projectManagement.service.ProjectService;
import com.projectManagement.service.TaskService;
import com.projectManagement.service.mapper.DtoMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultTaskService implements TaskService {

  @Autowired
  TaskRepository taskRepository;

  @Autowired
  UserRepository userRepository;

  @Override
  public List<TaskDto> findAll() {
    return taskRepository.findAll().stream().map(DtoMapper::toTaskDto).collect(Collectors.toList());
  }

  @Override
  public List<TaskDto> findAllMineTasks(Long userId) {
    return userRepository.findById(userId).stream()
      .flatMap((ob) -> ob.getProjects().stream())
      .flatMap(ob -> ob.getTasks().stream())
      .map(DtoMapper::toTaskDto)
      .collect(Collectors.toList());
  }

  @Override
  public void createTask(TaskDto task) {
    final TaskEntity entity = DtoMapper.toTaskEntity(task);
    entity.setCreateDate(LocalDateTime.now());
    taskRepository.save(entity);
  }
}
