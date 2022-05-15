package com.projectManagement.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.projectManagement.util.DateUtils;

import java.time.LocalDateTime;
import java.util.Set;

public class TaskDto {

  private final Long id;
  private final String name;
  private final String comment;
  private final String status;
  private final LocalDateTime createDate;
  private final Set<ReportDto> reports;

  @JsonCreator
  public TaskDto(
    @JsonProperty("id") Long id,
    @JsonProperty("name") String name,
    @JsonProperty("comment") String comment,
    @JsonProperty("status") String status,
    @JsonProperty("createDate") @JsonFormat(pattern = DateUtils.JSON_DATE_TIME_FORMAT) LocalDateTime createDate,
    @JsonProperty("reports") Set<ReportDto> reports) {
    this.id = id;
    this.name = name;
    this.comment = comment;
    this.status = status;
    this.createDate = createDate;
    this.reports = reports;
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

  public Set<ReportDto> getReports() {
    return reports;
  }

  @Override
  public String toString() {
    return "TaskDto{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", comment='" + comment + '\'' +
      ", status='" + status + '\'' +
      ", createDate=" + createDate +
      ", reports=" + reports +
      '}';
  }
}
