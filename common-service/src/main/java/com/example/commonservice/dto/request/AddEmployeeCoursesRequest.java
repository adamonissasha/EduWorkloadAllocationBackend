package com.example.commonservice.dto.request;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddEmployeeCoursesRequest {
    private List<EmployeeCourseRequest> employeeCourseRequests;
}