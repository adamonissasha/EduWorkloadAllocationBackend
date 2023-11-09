package com.example.commonservice.model;

import com.example.commonservice.model.id.SpecialityCourseId;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "speciality_course")
public class SpecialityCourse implements Serializable {
    @EmbeddedId
    private SpecialityCourseId id;

    @MapsId("specialityId")
    @ManyToOne
    @JoinColumn(name = "speciality_id")
    private Speciality speciality;

    @MapsId("courseId")
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
