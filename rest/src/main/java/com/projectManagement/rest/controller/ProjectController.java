package com.projectManagement.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import static com.projectManagement.rest.Navigation.CREATE_REPORT;
import static com.projectManagement.rest.Navigation.LOAD;
import static com.projectManagement.rest.Navigation.LOAD_ALL_MINE_PROJECTS;

import com.projectManagement.dto.EmptyDto;
import com.projectManagement.dto.ProjectDto;
import com.projectManagement.filter.ProjectFilter;
import com.projectManagement.service.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/project")
public class ProjectController {

    private static final String STATUS_200 = "200";
    private static final String STATUS_500 = "500";
    private static final String SUCCESSFUL_OPERATION = "Successful operation";
    private static final String INTERNAL_SERVER_ERROR = "Internal Server Error";

    @Autowired
    ProjectService projectService;

    /**
     * Required: {"page": "0", "size": "10"}
     */
    @PostMapping(LOAD)
    @Operation(summary = "Request for loading projects")
    @ApiResponses(
      value = {
        @ApiResponse(
          responseCode = STATUS_200,
          description = SUCCESSFUL_OPERATION,
          content = @Content(schema = @Schema(implementation = ResponseEntity.class))
        ),
        @ApiResponse(
          responseCode = STATUS_500,
          description = INTERNAL_SERVER_ERROR,
          content = @Content(schema = @Schema(implementation = EmptyDto.class))
        ),
      }
    )
    public ResponseEntity<?> getProjectsPage(@RequestBody ProjectFilter filter) {

        final List<ProjectDto> projects = projectService.findAll();
        return ResponseEntity.ok(projects);
    }

    @GetMapping(LOAD_ALL_MINE_PROJECTS)
    public ResponseEntity<?> getAllMineProjects(@RequestParam("userId") Long userId) {

        final List<ProjectDto> projects = projectService.findAllMineProjects(userId);
        return ResponseEntity.ok(projects);
    }

    @PutMapping(CREATE_REPORT)
    public ResponseEntity<?> createProject(@RequestBody ProjectDto project) {

        projectService.createProject(project);
        return ResponseEntity.ok().build();
    }
}
