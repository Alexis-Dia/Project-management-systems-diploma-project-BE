package com.projectManagement.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "project")
public class ProjectEntity implements Serializable {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_project_id")
  @SequenceGenerator(name = "seq_project_id", sequenceName = "seq_project_id", allocationSize = 1)
  private Long id;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "project_id")
  private Set<TaskEntity> tasks;

  @Column(name = "name", length = 100, nullable = false)
  private String name;

  @Column(name = "comment", length = 500)
  private String comment;

  @Column(name = "create_date", nullable = false)
  private LocalDateTime createDate;

  @Column(name = "hours")
  private Float hours;

  @Column(name = "priority")
  private Integer priority;

  @Column(name = "budget")
  private Float budget;

  protected ProjectEntity() {}

  public ProjectEntity(
    String name,
    String comment,
    LocalDateTime createDate,
    Float hours,
    Integer priority,
    Float budget
  ) {
    this.name = name;
    this.comment = comment;
    this.createDate = createDate;
    this.hours = hours;
    this.priority = priority;
    this.budget = budget;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public LocalDateTime getCreateDate() {
    return createDate;
  }

  public void setCreateDate(LocalDateTime createDate) {
    this.createDate = createDate;
  }

  public Set<TaskEntity> getTasks() {
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
    return "ProjectEntity{" +
      "id=" + id +
      ", tasks=" + tasks +
      ", name='" + name + '\'' +
      ", comment='" + comment + '\'' +
      ", createDate=" + createDate +
      ", hours=" + hours +
      ", priority=" + priority +
      ", budget=" + budget +
      '}';
  }
}
