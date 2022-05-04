package com.projectManagement.filter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.projectManagement.dto.TaskDto;
import com.projectManagement.filter.base.HasOrders;
import com.projectManagement.filter.base.HasPageable;
import com.projectManagement.filter.base.OrderBy;
import com.projectManagement.util.DateUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class ProjectFilter implements HasOrders, HasPageable {

  private final int page;
  private final int size;
  private final List<OrderBy> orders;
  private final String name;
  private final String comment;
  private final LocalDateTime createDate;
  private final Set<TaskDto> tasks;
  private final Float hours;
  private final Integer priority;
  private final Float budget;

  @JsonCreator
  public ProjectFilter(
    @JsonProperty("page") int page,
    @JsonProperty("size") int size,
    @JsonProperty("orders") List<OrderBy> orders,
    @JsonProperty("name") String name,
    @JsonProperty("comment") String comment,
    @JsonProperty("tasks") Set<TaskDto> tasks,
    @JsonProperty("createDate") @JsonFormat(pattern = DateUtils.JSON_DATE_TIME_FORMAT) LocalDateTime createDate,
    @JsonProperty("hours") Float hours,
    @JsonProperty("priority") Integer priority,
    @JsonProperty("budget") Float budget) {
    this.page = page;
    this.size = size;
    this.orders = orders;
    this.name = name;
    this.comment = comment;
    this.tasks = tasks;
    this.createDate = createDate;
    this.hours = hours;
    this.priority = priority;
    this.budget = budget;
  }

  @Override
  public List<OrderBy> getOrders() {
    return orders;
  }

  @Override
  public int getSize() {
    return size;
  }

  @Override
  public int getPage() {
    return page;
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

  public Float getHours() {
    return hours;
  }

  public Integer getPriority() {
    return priority;
  }

  public Float getBudget() {
    return budget;
  }
}
