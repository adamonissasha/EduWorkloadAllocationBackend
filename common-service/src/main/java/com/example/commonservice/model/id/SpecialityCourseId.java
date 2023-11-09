package com.example.commonservice.model.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class SpecialityCourseId implements Serializable {
    @Column(name = "speciality_id")
    private long specialityId;

    @Column(name = "course_id")
    private long courseId;
}
