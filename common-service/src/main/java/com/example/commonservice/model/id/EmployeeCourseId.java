package com.example.commonservice.model.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class EmployeeCourseId implements Serializable {
    @Column(name = "employee_id")
    private long employeeId;

    @Column(name = "course_id")
    private long courseId;
}
