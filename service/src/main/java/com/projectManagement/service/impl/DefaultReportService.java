package com.projectManagement.service.impl;

import com.projectManagement.dto.ReportDto;
import com.projectManagement.dto.TaskDto;
import com.projectManagement.entity.ReportEntity;
import com.projectManagement.entity.TaskEntity;
import com.projectManagement.repository.ReportRepository;
import com.projectManagement.repository.TaskRepository;
import com.projectManagement.service.ReportService;
import com.projectManagement.service.TaskService;
import com.projectManagement.service.mapper.DtoMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultReportService implements ReportService {

  @Autowired
  ReportRepository reportRepository;

  @Override
  public List<ReportDto> findAll() {
    return reportRepository.findAll().stream().map(DtoMapper::toReportDto).collect(Collectors.toList());
  }

  @Override
  public void createReport(ReportDto report) {
    final ReportEntity entity = DtoMapper.toReportEntity(report);
    entity.setCreateDate(LocalDateTime.now());
    reportRepository.save(entity);
  }
}
