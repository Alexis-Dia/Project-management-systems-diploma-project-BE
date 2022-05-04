package com.projectManagement.service.mapper;

import com.projectManagement.dto.ProjectDto;
import com.projectManagement.dto.TaskDto;
import com.projectManagement.dto.UserDto;
import com.projectManagement.entity.ProjectEntity;
import com.projectManagement.entity.TaskEntity;
import com.projectManagement.entity.UserEntity;

import java.util.Set;
import java.util.stream.Collectors;

public class DtoMapper {

    public static UserDto toUserDto(UserEntity userEntity) {
        if (userEntity != null) {
            return new UserDto(userEntity.getId(), userEntity.getLastName(), userEntity.getFirstName(),
                userEntity.getPatronymic(), userEntity.getBirthday(), userEntity.getLogin(), userEntity.getPassword(),
                userEntity.getMoney(), userEntity.getRole(), userEntity.getStatus());

        }
        return new UserDto();
    }

    public static ProjectDto toProjectDto(ProjectEntity projectEntity) {
        if (projectEntity != null) {
            return new ProjectDto(projectEntity.getId(), projectEntity.getName(), projectEntity.getName(),
              toApprovementDtoSet(projectEntity.getTasks()), projectEntity.getCreateDate(), projectEntity.getHours(),
              projectEntity.getPriority(), projectEntity.getBudget());
        }
        return null;
    }

    static Set<TaskDto> toApprovementDtoSet(Set<TaskEntity> entities) {
        return entities.stream().map(DtoMapper::toTaskDto).collect(Collectors.toSet());
    }

    public static TaskDto toTaskDto(TaskEntity taskEntity) {
        if (taskEntity != null) {
            return new TaskDto(taskEntity.getId(), taskEntity.getName(), taskEntity.getName(), taskEntity.getCreateDate());
        }
        return null;
    }

    public static ProjectEntity toProjectEntity(ProjectDto project) {
        if (project != null) {
            return new ProjectEntity(project.getName(), project.getName(),
              project.getCreateDate(), project.getHours(),
              project.getPriority(), project.getBudget());
        }
        return null;
    }
}
