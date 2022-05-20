package com.projectManagement.service.impl;

import com.projectManagement.dto.TaskDto;
import com.projectManagement.entity.ProjectEntity;
import com.projectManagement.entity.ReportEntity;
import com.projectManagement.entity.TaskEntity;
import com.projectManagement.entity.UserEntity;
import com.projectManagement.entity.UserStatus;
import com.projectManagement.repository.ProjectRepository;
import com.projectManagement.repository.TaskRepository;
import com.projectManagement.repository.UserRepository;
import com.projectManagement.service.TaskService;
import com.projectManagement.service.mapper.DtoMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DefaultTaskService implements TaskService {

  @Autowired
  ProjectRepository projectRepository;

  @Autowired
  TaskRepository taskRepository;

  @Autowired
  UserRepository userRepository;

  @Override
  public List<TaskDto> findAll() {
    return taskRepository.findAll().stream().map(DtoMapper::toTaskDto).collect(Collectors.toList());
  }

  @Override
  public List<TaskDto> findAllMineCurrentTasksInProgress(Long userId) {
    return userRepository.findById(userId).stream()
      .flatMap((ob) -> ob.getProjects().stream())
      .flatMap(ob -> ob.getTasks().stream())
      .filter(ob -> ob.getStatus().equals("IN_PROGRESS") || ob.getStatus().equals("FINISHED"))
      .sorted(Comparator.comparing(TaskEntity::getId))
      .map(DtoMapper::toTaskDto)
      .collect(Collectors.toList());
  }

  //TODO: needs to be refactored because of we have get new tasks from repository level
  @Override
  public List<TaskDto> findAllMineNewTasks(Long userId) {
    return userRepository.findById(userId).stream()
      .flatMap((ob) -> ob.getProjects().stream())
      .flatMap(ob -> ob.getTasks().stream())
      .filter(ob -> ob.getStatus().equals("NEW") || ob.getStatus().equals("ON_HOLD") || ob.getStatus().equals("FINISHED"))
      .sorted(Comparator.comparing(TaskEntity::getId))
      .map(DtoMapper::toTaskDto)
      .collect(Collectors.toList());
  }

  @Override
  @Transactional
  public void createTask(Long projectId, TaskDto task) {
    final TaskEntity entity = DtoMapper.toTaskEntity(task);
    entity.setStatus("NEW");
    final Optional<ProjectEntity> project = projectRepository.findById(projectId);
    final ProjectEntity projectEntity = project.get();
    final Set<TaskEntity> tasks = projectEntity.getTasks();
    tasks.add(entity);
    projectRepository.save(projectEntity);
  }

  @Override
  @Transactional
  public TaskDto changeTaskStatus(Long taskId, String status, String email) {
    final TaskEntity task = taskRepository.findById(taskId).get();
    task.setStatus(status);
    final TaskEntity entity = taskRepository.save(task);
    final UserEntity user = userRepository.findByLogin(email).get();
    if (status == "IN_PROGRESS") {
      user.setStatus(UserStatus.BUSY);
    } else {
      user.setStatus(UserStatus.FREE);
    }

    return DtoMapper.toTaskDto(entity);
  }
}
