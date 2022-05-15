package com.projectManagement.service;

import com.projectManagement.dto.ReportDto;

import java.util.List;

public interface ReportService {

  List<ReportDto> findAll();

  void createReport(ReportDto report);
}
