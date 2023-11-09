package com.example.commonservice.model;

import com.example.commonservice.model.enums.CourseType;
import com.example.commonservice.model.id.EmployeeCourseId;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "employee_course")
public class EmployeeCourse implements Serializable {
    @EmbeddedId
    private EmployeeCourseId id;

    @MapsId("userId")
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @MapsId("courseId")
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Enumerated(EnumType.STRING)
    private CourseType courseType;
}
