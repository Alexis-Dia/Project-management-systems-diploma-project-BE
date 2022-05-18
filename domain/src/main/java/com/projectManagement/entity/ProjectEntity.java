package com.projectManagement.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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

  @ManyToMany
  @JoinTable(name = "project_to_user", joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
  private Set<UserEntity> users;

  @OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true)
  @JoinColumn(name = "project_id")
  private Set<TaskEntity> tasks;

  @Column(name = "name", length = 100, nullable = false)
  private String name;

  @Column(name = "comment", length = 500)
  private String comment;

  @Column(name = "status", length = 50)
  private String status;

  @Column(name = "create_date", nullable = false)
  private LocalDateTime createDate;

  @Column(name = "hours")
  private Float hours;

  @Column(name = "priority")
  private Integer priority;

  @Column(name = "budget")
  private Float budget;

  protected ProjectEntity() {}

  /**Pay attention, that here we don't need to use tasks and users**/
  public ProjectEntity(
    String name,
    String comment,
    String status,
    LocalDateTime createDate,
    Float hours,
    Integer priority,
    Float budget
  ) {
    this.name = name;
    this.comment = comment;
    this.status = status;
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

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
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

  public Float getHours() {
    return hours;
  }

  public Integer getPriority() {
    return priority;
  }

  public Float getBudget() {
    return budget;
  }

  public Set<UserEntity> getUsers() {
    return users;
  }

  public void setUsers(Set<UserEntity> users) {
    this.users = users;
  }

  public Set<TaskEntity> getTasks() {
    return tasks;
  }

  public void setTasks(Set<TaskEntity> tasks) {
    this.tasks = tasks;
  }

  @Override
  public String toString() {
    return "ProjectEntity{" +
      "id=" + id +
      ", users=" + users +
      ", tasks=" + tasks +
      ", name='" + name + '\'' +
      ", comment='" + comment + '\'' +
      ", status='" + status + '\'' +
      ", createDate=" + createDate +
      ", hours=" + hours +
      ", priority=" + priority +
      ", budget=" + budget +
      '}';
  }
}
