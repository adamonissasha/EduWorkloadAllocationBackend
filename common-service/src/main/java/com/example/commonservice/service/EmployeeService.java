package com.example.commonservice.service;

import com.example.commonservice.dto.request.EmployeeRequest;
import com.example.commonservice.dto.response.EmployeeResponse;

import java.util.List;

public interface EmployeeService {
    EmployeeResponse createEmployee(EmployeeRequest employeeRequest);

    EmployeeResponse editEmployee(long id, EmployeeRequest employeeRequest);

    void deleteEmployee(long id);

    List<EmployeeResponse> getEmployees();

    EmployeeResponse getEmployeeById(long id);

    List<String> getAcademicDegrees();

    List<String> getJobTitles();
}
