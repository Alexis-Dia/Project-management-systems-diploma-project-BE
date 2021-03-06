package com.projectManagement.service;

import com.projectManagement.dto.ProjectDto;
import com.projectManagement.dto.UserDto;
import com.projectManagement.repository.ProjectRepository;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface ProjectService {

  List<ProjectDto> findAll();

  List<ProjectDto> findAllMineProjects(Long userId);

  void createProject(ProjectDto project);

  ProjectDto addUserToTheProject(Long userId, Long projectId);

  ProjectDto removeUserFromTheProject(Long userId, Long projectId);
}
