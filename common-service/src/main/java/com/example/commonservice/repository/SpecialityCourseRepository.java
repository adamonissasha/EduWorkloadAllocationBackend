package com.example.commonservice.repository;

import com.example.commonservice.model.SpecialityCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecialityCourseRepository extends JpaRepository<SpecialityCourse, Long> {
    List<SpecialityCourse> findAllBySpecialityId(Long specialityId);
}
