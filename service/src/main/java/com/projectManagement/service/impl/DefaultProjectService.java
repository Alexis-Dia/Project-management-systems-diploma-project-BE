package com.projectManagement.service.impl;

import com.projectManagement.dto.ProjectDto;
import com.projectManagement.dto.TaskDto;
import com.projectManagement.dto.UserDto;
import com.projectManagement.entity.ProjectEntity;
import com.projectManagement.entity.UserEntity;
import com.projectManagement.repository.ProjectRepository;
import com.projectManagement.repository.UserRepository;
import com.projectManagement.service.ProjectService;
import com.projectManagement.service.mapper.DtoMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DefaultProjectService implements ProjectService {

  @Autowired
  ProjectRepository projectRepository;

  @Override
  public List<ProjectDto> findAll() {
    return projectRepository.findAll().stream().map(DtoMapper::toProjectDto).collect(Collectors.toList());
  }

  @Autowired
  UserRepository userRepository;

  @Override
  public List<ProjectDto> findAllMineProjects(Long userId) {
    return userRepository.findById(userId).stream()
      .flatMap((ob) -> ob.getProjects().stream())
      .map(DtoMapper::toProjectDto)
      .collect(Collectors.toList());
  }

  @Override
  public void createProject(ProjectDto project) {
    final ProjectEntity entity = DtoMapper.toProjectEntity(project);
    entity.setCreateDate(LocalDateTime.now());
    projectRepository.save(entity);
  }

  @Override
  @Transactional
  public ProjectDto addUserToTheProject(Long userId, Long projectId) {
    final ProjectEntity project = projectRepository.findById(projectId).get();
    final UserEntity user = userRepository.findById(userId).get();
    final Set<UserEntity> users = project.getUsers();
    users.add(user);
    project.setUsers(users);
    final ProjectDto projectDto = DtoMapper.toProjectDto(projectRepository.save(project));
    return projectDto;
  }

  @Override
  @Transactional
  public ProjectDto removeUserFromTheProject(Long userId, Long projectId) {
    final ProjectEntity project = projectRepository.findById(projectId).get();
    final UserEntity user = userRepository.findById(userId).get();
    final Set<UserEntity> users = project.getUsers();
    users.remove(user);
    project.setUsers(users);
    final ProjectDto projectDto = DtoMapper.toProjectDto(projectRepository.save(project));
    return projectDto;
  }
}
