package com.example.commonservice.service.impl;

import com.example.commonservice.dto.request.DepartmentRequest;
import com.example.commonservice.dto.response.DepartmentResponse;
import com.example.commonservice.exception.DepartmentNotFoundException;
import com.example.commonservice.model.Department;
import com.example.commonservice.repository.DepartmentRepository;
import com.example.commonservice.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private static final String DEPARTMENT_NOT_FOUND = "Кафедра не найдена!";
    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    @Override
    public DepartmentResponse createDepartment(DepartmentRequest departmentRequest) {
        Department newDepartment = mapDepartmentRequestToDepartment(departmentRequest);
        newDepartment = departmentRepository.save(newDepartment);
        return mapDepartmentToDepartmentResponse(newDepartment);
    }

    @Override
    public DepartmentResponse editDepartment(long id, DepartmentRequest departmentRequest) {
        Department updatedDepartment = mapDepartmentRequestToDepartment(departmentRequest);
        Department existingDepartment = departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(DEPARTMENT_NOT_FOUND));
        updatedDepartment.setId(existingDepartment.getId());
        updatedDepartment = departmentRepository.save(updatedDepartment);
        return mapDepartmentToDepartmentResponse(updatedDepartment);
    }

    @Override
    public void deleteDepartment(long id) {
        departmentRepository.findById(id)
                .ifPresentOrElse(
                        department -> departmentRepository.deleteById(id),
                        () -> {
                            throw new DepartmentNotFoundException(DEPARTMENT_NOT_FOUND);
                        }
                );
    }

    @Override
    public List<DepartmentResponse> getDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(this::mapDepartmentToDepartmentResponse)
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentResponse getDepartmentById(long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(DEPARTMENT_NOT_FOUND));
        return mapDepartmentToDepartmentResponse(department);
    }


    private Department mapDepartmentRequestToDepartment(DepartmentRequest departmentRequest) {
        return modelMapper.map(departmentRequest, Department.class);
    }

    private DepartmentResponse mapDepartmentToDepartmentResponse(Department department) {
        return modelMapper.map(department, DepartmentResponse.class);
    }
}
