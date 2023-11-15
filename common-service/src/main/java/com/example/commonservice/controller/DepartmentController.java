package com.example.commonservice.controller;

import com.example.commonservice.dto.request.DepartmentRequest;
import com.example.commonservice.dto.response.DepartmentResponse;
import com.example.commonservice.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/common/department")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public DepartmentResponse createDepartment(@RequestBody DepartmentRequest departmentRequest) {
        return departmentService.createDepartment(departmentRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DepartmentResponse editDepartment(@PathVariable("id") long id,
                                             @RequestBody DepartmentRequest departmentRequest) {
        return departmentService.editDepartment(id, departmentRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteDepartment(@PathVariable("id") long id) {
        departmentService.deleteDepartment(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<DepartmentResponse> getDepartments() {
        return departmentService.getDepartments();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DepartmentResponse getDepartment(@PathVariable("id") long id) {
        return departmentService.getDepartmentById(id);
    }
}
