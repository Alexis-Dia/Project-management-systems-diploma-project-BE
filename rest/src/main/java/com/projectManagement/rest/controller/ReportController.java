package com.projectManagement.rest.controller;

import static com.projectManagement.rest.Navigation.CREATE;
import static com.projectManagement.rest.Navigation.LOAD;

import com.projectManagement.dto.ReportDto;
import com.projectManagement.dto.TaskDto;
import com.projectManagement.service.ReportService;
import com.projectManagement.service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/report")
public class ReportController {

    private static final String STATUS_200 = "200";
    private static final String STATUS_500 = "500";
    private static final String SUCCESSFUL_OPERATION = "Successful operation";
    private static final String INTERNAL_SERVER_ERROR = "Internal Server Error";

    @Autowired
    ReportService reportService;

    @PostMapping(LOAD)
    public ResponseEntity<?> getAllReports() {

        final List<ReportDto> reports = reportService.findAll();
        return ResponseEntity.ok(reports);
    }

    @PutMapping(CREATE)
    public ResponseEntity<?> createProject(@RequestBody ReportDto report) {

        reportService.createReport(report);
        return ResponseEntity.ok().build();
    }
}
