package com.example.commonservice.service;

import com.example.commonservice.dto.request.AddEmployeeCoursesRequest;
import com.example.commonservice.dto.response.AddEmployeeCoursesResponse;

public interface EmployeeCourseService {
    AddEmployeeCoursesResponse addEmployeeCourses(long employeeId, AddEmployeeCoursesRequest employeeCourseRequests);

    void deleteEmployeeCourse(long employeeId, long id);

    AddEmployeeCoursesResponse getEmployeeCourses(long employeeId);
}
