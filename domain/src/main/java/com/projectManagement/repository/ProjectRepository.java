package com.projectManagement.repository;

import com.projectManagement.entity.ProjectEntity;
import com.projectManagement.entity.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {
}
