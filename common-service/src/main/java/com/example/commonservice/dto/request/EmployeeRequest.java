package com.example.commonservice.dto.request;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeRequest {
    private String firstName;
    private String secondName;
    private String lastName;
    private String email;
    private String phone;
    private String jobTitle;
    private String academicDegree;
    private LocalDate dateOfEmployment;
    private long departmentId;
}
