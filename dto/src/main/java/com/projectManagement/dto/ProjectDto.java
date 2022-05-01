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
  private final LocalDateTime createDate;
  private final Set<TaskDto> tasks;

//  public ProjectDto() {}

  @JsonCreator
  public ProjectDto(
    @JsonProperty("id") Long id,
    @JsonProperty("name") String name,
    @JsonProperty("comment") String comment,
    @JsonProperty("tasks") Set<TaskDto> tasks,
    @JsonProperty("createDate") @JsonFormat(pattern = DateUtils.JSON_DATE_TIME_FORMAT) LocalDateTime createDate) {
    this.id = id;
    this.name = name;
    this.comment = comment;
    this.tasks = tasks;
    this.createDate = createDate;
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

  public LocalDateTime getCreateDate() {
    return createDate;
  }

  public Set<TaskDto> getTasks() {
    return tasks;
  }

  @Override
  public String toString() {
    return "ProjectDto{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", comment='" + comment + '\'' +
      ", createDate=" + createDate +
      ", tasks=" + tasks +
      '}';
  }
}
