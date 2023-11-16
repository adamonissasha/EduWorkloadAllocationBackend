package com.example.commonservice.controller;

import com.example.commonservice.dto.request.AddEmployeeCoursesRequest;
import com.example.commonservice.dto.response.AddEmployeeCoursesResponse;
import com.example.commonservice.service.EmployeeCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/common/course/employee/{employeeId}")
@RequiredArgsConstructor
public class EmployeeCourseController {
    private final EmployeeCourseService employeeCourseService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public AddEmployeeCoursesResponse addEmployeeCourses(@PathVariable("employeeId") long employeeId,
                                                         @RequestBody AddEmployeeCoursesRequest employeeCourseRequests) {
        return employeeCourseService.addEmployeeCourses(employeeId, employeeCourseRequests);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEmployeeCourse(@PathVariable("employeeId") long employeeId,
                                     @PathVariable("id") long id) {
        employeeCourseService.deleteEmployeeCourse(employeeId, id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public AddEmployeeCoursesResponse getEmployeeCourses(@PathVariable("employeeId") long employeeId) {
        return employeeCourseService.getEmployeeCourses(employeeId);
    }
}
