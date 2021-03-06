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
@Table(name = "task")
public class TaskEntity implements Serializable {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_task_id")
  @SequenceGenerator(name = "seq_task_id", sequenceName = "seq_task_id", allocationSize = 1)
  private Long id;

  @Column(name = "name", length = 100, nullable = false)
  private String name;

  @Column(name = "comment", length = 500)
  private String comment;

  @Column(name = "status", length = 50)
  private String status;

  @Column(name = "create_date", nullable = false)
  private LocalDateTime createDate;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "task_id")
  private Set<ReportEntity> reports;

  protected TaskEntity() {}

  public TaskEntity(
    String name,
    String comment,
    String status,
    LocalDateTime createDate
  ) {
    this.name = name;
    this.comment = comment;
    this.status = status;
    this.createDate = createDate;
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

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public LocalDateTime getCreateDate() {
    return createDate;
  }

  public void setCreateDate(LocalDateTime createDate) {
    this.createDate = createDate;
  }

  public Set<ReportEntity> getReports() {
    return reports;
  }

  @Override
  public String toString() {
    return "TaskEntity{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", comment='" + comment + '\'' +
      ", status='" + status + '\'' +
      ", createDate=" + createDate +
      '}';
  }
}
