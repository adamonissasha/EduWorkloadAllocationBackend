package com.example.commonservice.model;

import com.example.commonservice.model.enums.AcademicDegree;
import com.example.commonservice.model.enums.JobTitle;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;

    private String secondName;

    private String lastName;

    private String email;

    private String phone;

    private LocalDate dateOfEmployment;

    @Enumerated(EnumType.STRING)
    private JobTitle jobTitle;

    @Enumerated(EnumType.STRING)
    private AcademicDegree academicDegree;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;
}
