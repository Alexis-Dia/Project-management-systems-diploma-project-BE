package com.projectManagement.service.mapper;

import com.projectManagement.dto.ProjectDto;
import com.projectManagement.dto.ReportDto;
import com.projectManagement.dto.TaskDto;
import com.projectManagement.dto.UserDto;
import com.projectManagement.entity.ProjectEntity;
import com.projectManagement.entity.ReportEntity;
import com.projectManagement.entity.TaskEntity;
import com.projectManagement.entity.UserEntity;

import java.util.Set;
import java.util.stream.Collectors;

public class DtoMapper {

    public static UserDto toUserDto(UserEntity userEntity) {
        if (userEntity != null) {
            return new UserDto(userEntity.getId(), userEntity.getLastName(), userEntity.getFirstName(),
                userEntity.getPatronymic(), userEntity.getBirthday(), userEntity.getLogin(), userEntity.getPassword(),
                userEntity.getMoney(), userEntity.getRole(), userEntity.getStatus(), toProjectDtoSetWithoutUsers(userEntity.getProjects()));

        }
        return new UserDto();
    }

    public static UserDto toUserDtoWithoutProjects(UserEntity userEntity) {
        if (userEntity != null) {
            return new UserDto(userEntity.getId(), userEntity.getLastName(), userEntity.getFirstName(),
              userEntity.getPatronymic(), userEntity.getBirthday(), userEntity.getLogin(), userEntity.getPassword(),
              userEntity.getMoney(), userEntity.getRole(), userEntity.getStatus(), null);

        }
        return new UserDto();
    }

    static Set<ProjectDto> toProjectDtoSet(Set<ProjectEntity> entities) {
        return entities.stream().map(DtoMapper::toProjectDto).collect(Collectors.toSet());
    }

    static Set<ProjectDto> toProjectDtoSetWithoutUsers(Set<ProjectEntity> entities) {
        return entities.stream().map(DtoMapper::toProjectDtoWithoutUsers).collect(Collectors.toSet());
    }

    static Set<UserDto> toUserDtoSet(Set<UserEntity> entities) {
        return entities.stream().map(DtoMapper::toUserDto).collect(Collectors.toSet());
    }

    static Set<TaskDto> toTaskDtoSet(Set<TaskEntity> entities) {
        return entities.stream().map(DtoMapper::toTaskDto).collect(Collectors.toSet());
    }

    static Set<ReportDto> toReportDtoSet(Set<ReportEntity> entities) {
        return entities.stream().map(DtoMapper::toReportDto).collect(Collectors.toSet());
    }

    public static ProjectDto toProjectDto(ProjectEntity projectEntity) {
        if (projectEntity != null) {
            return new ProjectDto(projectEntity.getId(), projectEntity.getName(), projectEntity.getComment(), projectEntity.getStatus(),
              toUserDtoSet(projectEntity.getUsers()), toTaskDtoSet(projectEntity.getTasks()), projectEntity.getCreateDate(),
              projectEntity.getHours(), projectEntity.getPriority(), projectEntity.getBudget());
        }
        return null;
    }

    public static ProjectDto toProjectDtoWithoutUsers(ProjectEntity projectEntity) {
        if (projectEntity != null) {
            return new ProjectDto(projectEntity.getId(), projectEntity.getName(), projectEntity.getComment(), projectEntity.getStatus(),
              null, toTaskDtoSet(projectEntity.getTasks()), projectEntity.getCreateDate(),
              projectEntity.getHours(), projectEntity.getPriority(), projectEntity.getBudget());
        }
        return null;
    }

    public static TaskDto toTaskDto(TaskEntity taskEntity) {
        if (taskEntity != null) {
            return new TaskDto(taskEntity.getId(), taskEntity.getName(), taskEntity.getComment(), taskEntity.getStatus(),
              taskEntity.getCreateDate(), toReportDtoSet(taskEntity.getReports()));
        }
        return null;
    }

    public static ReportDto toReportDto(ReportEntity reportEntity) {
        if (reportEntity != null) {
            return new ReportDto(reportEntity.getId(), reportEntity.getName(), reportEntity.getName(), reportEntity.getCreateDate());
        }
        return null;
    }

    public static ProjectEntity toProjectEntity(ProjectDto project) {
        if (project != null) {
            return new ProjectEntity(project.getName(), project.getComment(), project.getStatus(),
              project.getCreateDate(), project.getHours(),
              project.getPriority(), project.getBudget());
        }
        return null;
    }

    public static TaskEntity toTaskEntity(TaskDto task) {
        if (task != null) {
            return new TaskEntity(task.getName(), task.getComment(), task.getStatus(),
              task.getCreateDate());
        }
        return null;
    }

    public static ReportEntity toReportEntity(ReportDto report) {
        if (report != null) {
            return new ReportEntity(report.getName(), report.getComment(),
              report.getCreateDate());
        }
        return null;
    }

}
