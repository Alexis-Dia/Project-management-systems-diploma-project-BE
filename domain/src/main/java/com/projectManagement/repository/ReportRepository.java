package com.projectManagement.repository;

import com.projectManagement.entity.ReportEntity;
import com.projectManagement.entity.TaskEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<ReportEntity, Long> {
}
