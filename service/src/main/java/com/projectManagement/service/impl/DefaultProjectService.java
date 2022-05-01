package com.projectManagement.service.impl;

import com.projectManagement.dto.ProjectDto;
import com.projectManagement.dto.UserDto;
import com.projectManagement.repository.ProjectRepository;
import com.projectManagement.service.ProjectService;
import com.projectManagement.service.mapper.DtoMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultProjectService implements ProjectService {

  @Autowired
  ProjectRepository projectRepository;

  @Override
  public List<ProjectDto> findAll() {
    return projectRepository.findAll().stream().map(DtoMapper::toProjectDto).collect(Collectors.toList());
  }
}
