package com.projectManagement.service;

import com.projectManagement.dto.ReportDto;

import java.util.List;

public interface ReportService {

  List<ReportDto> findAll();

  List<ReportDto> findAllMineReportsByTaskId(Long userId, Long taskId);

  void createReport(Long taskId, ReportDto report);
}
