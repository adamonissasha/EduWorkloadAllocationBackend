package com.example.commonservice.repository;

import com.example.commonservice.model.EmployeeCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeCourseRepository extends JpaRepository<EmployeeCourse, Long> {
    List<EmployeeCourse> findAllByEmployeeId(Long employeeId);
}
