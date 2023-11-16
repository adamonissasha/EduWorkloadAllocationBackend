package com.example.commonservice.dto.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeCourseResponse {
    private long id;
    private String courseName;
    private String courseType;
}