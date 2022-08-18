package com.eadmin.user.service.repository;

import com.eadmin.user.service.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
