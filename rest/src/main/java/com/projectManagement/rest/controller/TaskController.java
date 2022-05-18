package com.projectManagement.rest.controller;

import static com.projectManagement.rest.Navigation.CREATE_TASK;
import static com.projectManagement.rest.Navigation.LOAD;
import static com.projectManagement.rest.Navigation.LOAD_ALL_MINE_NEW_TASKS_NEW;
import static com.projectManagement.rest.Navigation.LOAD_ALL_MINE_TASKS_IN_PROGRESS;

import com.projectManagement.dto.TaskDto;
import com.projectManagement.service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/task")
public class TaskController {

    private static final String STATUS_200 = "200";
    private static final String STATUS_500 = "500";
    private static final String SUCCESSFUL_OPERATION = "Successful operation";
    private static final String INTERNAL_SERVER_ERROR = "Internal Server Error";

    @Autowired
    TaskService taskService;

    @PostMapping(LOAD)
    public ResponseEntity<?> getAllTasks() {

        final List<TaskDto> tasks = taskService.findAll();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping(LOAD_ALL_MINE_TASKS_IN_PROGRESS)
    public ResponseEntity<?> getAllMineCurrentTasks(@RequestParam("userId") Long userId) {

        final List<TaskDto> tasks = taskService.findAllMineCurrentTasksInProgress(userId);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping(LOAD_ALL_MINE_NEW_TASKS_NEW)
    public ResponseEntity<?> getAllMineNewTasks(@RequestParam("userId") Long userId) {

        final List<TaskDto> tasks = taskService.findAllMineNewTasks(userId);
        return ResponseEntity.ok(tasks);
    }

    @PostMapping(CREATE_TASK)
    public ResponseEntity<?> createTask(@PathVariable("projectId") Long projectId, @RequestBody TaskDto task) {

        taskService.createTask(projectId, task);
        return ResponseEntity.ok().build();
    }
}
