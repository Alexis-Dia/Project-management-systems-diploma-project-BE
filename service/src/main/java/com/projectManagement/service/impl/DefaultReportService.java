package com.projectManagement.service.impl;

import com.projectManagement.dto.ReportDto;
import com.projectManagement.dto.TaskDto;
import com.projectManagement.entity.ReportEntity;
import com.projectManagement.entity.TaskEntity;
import com.projectManagement.repository.ReportRepository;
import com.projectManagement.repository.TaskRepository;
import com.projectManagement.repository.UserRepository;
import com.projectManagement.service.ReportService;
import com.projectManagement.service.TaskService;
import com.projectManagement.service.mapper.DtoMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DefaultReportService implements ReportService {

  @Autowired
  ReportRepository reportRepository;

  @Autowired
  TaskRepository taskRepository;

  @Autowired
  UserRepository userRepository;

  @Override
  public List<ReportDto> findAll() {
    return reportRepository.findAll().stream().map(DtoMapper::toReportDto).collect(Collectors.toList());
  }

  @Override
  public List<ReportDto> findAllMineReportsByTaskId(Long userId, Long taskId) {
    return userRepository.findById(userId).stream()
      .flatMap(ob -> ob.getProjects().stream())
      .flatMap(ob -> ob.getTasks().stream())
      .filter(ob -> ob.getId().equals(taskId))
      .flatMap(ob -> ob.getReports().stream())
      .sorted(Comparator.comparing(ReportEntity::getId))
      .map(DtoMapper::toReportDto)
      .collect(Collectors.toList());
  }

  @Override
  @Transactional
  public void createReport(Long taskId, ReportDto report) {
    final ReportEntity entity = DtoMapper.toReportEntity(report);
    final Optional<TaskEntity> task = taskRepository.findById(taskId);
    final TaskEntity taskEntity = task.get();
    final Set<ReportEntity> reports = taskEntity.getReports();
    reports.add(entity);
    taskRepository.save(taskEntity);
  }
}
