package com.example.commonservice.controller;

import com.example.commonservice.dto.request.EmployeeRequest;
import com.example.commonservice.dto.response.EmployeeResponse;
import com.example.commonservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/common/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResponse createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        return employeeService.createEmployee(employeeRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeResponse editEmployee(@PathVariable("id") long id,
                                         @RequestBody EmployeeRequest employeeRequest) {
        return employeeService.editEmployee(id, employeeRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEmployee(@PathVariable("id") long id) {
        employeeService.deleteEmployee(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeResponse> getEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeResponse getEmployees(@PathVariable("id") long id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/academic-degree")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getAcademicDegrees() {
        return employeeService.getAcademicDegrees();
    }

    @GetMapping("/job-title")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getJobTitles() {
        return employeeService.getJobTitles();
    }
}
