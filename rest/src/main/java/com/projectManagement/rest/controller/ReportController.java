package com.projectManagement.rest.controller;

import static com.projectManagement.rest.Navigation.CREATE_REPORT;
import static com.projectManagement.rest.Navigation.GET_BY_TASK_ID;
import static com.projectManagement.rest.Navigation.LOAD;

import com.projectManagement.dto.ReportDto;
import com.projectManagement.dto.UserDto;
import com.projectManagement.service.ReportService;
import com.projectManagement.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/report")
public class ReportController {

    private static final String STATUS_200 = "200";
    private static final String STATUS_500 = "500";
    private static final String SUCCESSFUL_OPERATION = "Successful operation";
    private static final String INTERNAL_SERVER_ERROR = "Internal Server Error";

    @Autowired
    ReportService reportService;

    @Autowired
    UserService userService;

    @PostMapping(LOAD)
    public ResponseEntity<?> getAllReports() {

        final List<ReportDto> reports = reportService.findAll();
        return ResponseEntity.ok(reports);
    }

    @GetMapping(GET_BY_TASK_ID)
    public ResponseEntity<?> getAllMineProjects(@RequestParam("taskId") Long taskId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        final String name = auth.getName();
        final Optional<UserDto> user = userService.findByLogin(name);
        final List<ReportDto> reports = reportService.findAllMineReportsByTaskId(user.get().getUserID(), taskId);
        return ResponseEntity.ok(reports);
    }

    @PostMapping(CREATE_REPORT)
    public ResponseEntity<?> createReport(@PathVariable("taskId") Long taskId, @RequestBody ReportDto report) {

        reportService.createReport(taskId, report);
        return ResponseEntity.ok().build();
    }
}
