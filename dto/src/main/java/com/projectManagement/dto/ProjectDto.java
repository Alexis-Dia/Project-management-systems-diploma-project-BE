package com.projectManagement.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.projectManagement.util.DateUtils;

import java.time.LocalDateTime;
import java.util.Set;

public class ProjectDto {

  private final Long id;
  private final String name;
  private final String comment;
  private final String status;
  private final LocalDateTime createDate;
  private final Set<UserDto> users;
  private final Set<TaskDto> tasks;
  private final Float hours;
  private final Integer priority;
  private final Float budget;

  @JsonCreator
  public ProjectDto(
    @JsonProperty("id") Long id,
    @JsonProperty("name") String name,
    @JsonProperty("comment") String comment,
    @JsonProperty("status") String status,
    @JsonProperty("users") Set<UserDto> users,
    @JsonProperty("tasks") Set<TaskDto> tasks,
    @JsonProperty("createDate") @JsonFormat(pattern = DateUtils.JSON_DATE_TIME_FORMAT) LocalDateTime createDate,
    @JsonProperty("hours") Float hours,
    @JsonProperty("priority") Integer priority,
    @JsonProperty("budget") Float budget
    ) {
    this.id = id;
    this.name = name;
    this.comment = comment;
    this.status = status;
    this.users = users;
    this.tasks = tasks;
    this.createDate = createDate;
    this.hours = hours;
    this.priority = priority;
    this.budget = budget;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getComment() {
    return comment;
  }

  public String getStatus() {
    return status;
  }

  public LocalDateTime getCreateDate() {
    return createDate;
  }

  public Set<UserDto> getUsers() {
    return users;
  }

  public Set<TaskDto> getTasks() {
    return tasks;
  }

  public Float getHours() {
    return hours;
  }

  public Integer getPriority() {
    return priority;
  }

  public Float getBudget() {
    return budget;
  }

  @Override
  public String toString() {
    return "ProjectDto{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", comment='" + comment + '\'' +
      ", status='" + status + '\'' +
      ", createDate=" + createDate +
      ", users=" + users +
      ", tasks=" + tasks +
      ", hours=" + hours +
      ", priority=" + priority +
      ", budget=" + budget +
      '}';
  }
}
