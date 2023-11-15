package com.example.commonservice.service;

import com.example.commonservice.dto.request.DepartmentRequest;
import com.example.commonservice.dto.response.DepartmentResponse;

import java.util.List;

public interface DepartmentService {
    DepartmentResponse createDepartment(DepartmentRequest departmentRequest);

    DepartmentResponse editDepartment(long id, DepartmentRequest departmentRequest);

    void deleteDepartment(long id);

    List<DepartmentResponse> getDepartments();

    DepartmentResponse getDepartmentById(long id);
}
