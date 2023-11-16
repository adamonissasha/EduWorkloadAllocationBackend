package com.example.commonservice.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeCourseRequest {
    private Long courseId;
    private String courseType;
}