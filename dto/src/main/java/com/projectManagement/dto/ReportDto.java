package com.projectManagement.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.projectManagement.util.DateUtils;

import java.time.LocalDateTime;

public class ReportDto {

  private final Long id;
  private final String name;
  private final String comment;
  private final LocalDateTime createDate;

  @JsonCreator
  public ReportDto(
    @JsonProperty("id") Long id,
    @JsonProperty("name") String name,
    @JsonProperty("comment") String comment,
    @JsonProperty("createDate") @JsonFormat(pattern = DateUtils.JSON_DATE_TIME_FORMAT) LocalDateTime createDate) {
    this.id = id;
    this.name = name;
    this.comment = comment;
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

  @Override
  public String toString() {
    return "ReportDto{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", comment='" + comment + '\'' +
      ", createDate=" + createDate +
      '}';
  }
}
