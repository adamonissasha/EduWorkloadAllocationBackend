package com.example.commonservice.service.impl;

import com.example.commonservice.dto.request.EmployeeRequest;
import com.example.commonservice.dto.response.EmployeeResponse;
import com.example.commonservice.exception.EmployeeNotFoundException;
import com.example.commonservice.model.Employee;
import com.example.commonservice.model.enums.AcademicDegree;
import com.example.commonservice.model.enums.JobTitle;
import com.example.commonservice.repository.DepartmentRepository;
import com.example.commonservice.repository.EmployeeRepository;
import com.example.commonservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private static final String EMPLOYEE_NOT_FOUND = "Сотрудник не найден!";
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    @Override
    public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) {
        Employee newEmployee = mapEmployeeRequestToEmployee(employeeRequest);
        newEmployee = employeeRepository.save(newEmployee);

        return mapEmployeeToEmployeeResponse(newEmployee);
    }

    @Override
    public EmployeeResponse editEmployee(long id, EmployeeRequest employeeRequest) {
        Employee updatedEmployee = mapEmployeeRequestToEmployee(employeeRequest);
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND));
        updatedEmployee.setId(existingEmployee.getId());
        updatedEmployee = employeeRepository.save(updatedEmployee);
        return mapEmployeeToEmployeeResponse(updatedEmployee);
    }

    @Override
    public void deleteEmployee(long id) {
        employeeRepository.findById(id)
                .ifPresentOrElse(
                        employee -> employeeRepository.deleteById(id),
                        () -> {
                            throw new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND);
                        }
                );
    }

    @Override
    public List<EmployeeResponse> getEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(this::mapEmployeeToEmployeeResponse)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeResponse getEmployeeById(long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND));
        return mapEmployeeToEmployeeResponse(employee);
    }

    @Override
    public List<String> getJobTitles() {
        return Arrays.stream(JobTitle.values()).map(JobTitle::getName).toList();
    }

    @Override
    public List<String> getAcademicDegrees() {
        return Arrays.stream(AcademicDegree.values()).map(AcademicDegree::getName).toList();
    }


    private Employee mapEmployeeRequestToEmployee(EmployeeRequest employeeRequest) {
        Employee employee = modelMapper.map(employeeRequest, Employee.class);
        employee.setJobTitle(JobTitle.getByName(employeeRequest.getJobTitle()));
        employee.setAcademicDegree(AcademicDegree.getByName(employeeRequest.getAcademicDegree()));
        return employee;
    }

    private EmployeeResponse mapEmployeeToEmployeeResponse(Employee employee) {
        EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);
        employeeResponse.setDepartmentAbbreviation(departmentRepository.findById(employee.getDepartment().getId()).get().getAbbreviation());
        employeeResponse.setJobTitle(JobTitle.valueOf(employeeResponse.getJobTitle()).getName());
        employeeResponse.setAcademicDegree(AcademicDegree.valueOf(employeeResponse.getAcademicDegree()).getName());
        return employeeResponse;
    }
}
