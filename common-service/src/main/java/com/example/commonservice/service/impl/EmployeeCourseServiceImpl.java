package com.example.commonservice.service.impl;

import com.example.commonservice.dto.request.AddEmployeeCoursesRequest;
import com.example.commonservice.dto.request.EmployeeCourseRequest;
import com.example.commonservice.dto.response.AddEmployeeCoursesResponse;
import com.example.commonservice.dto.response.EmployeeCourseResponse;
import com.example.commonservice.exception.CourseNotFoundException;
import com.example.commonservice.exception.EmployeeCourseNotFoundException;
import com.example.commonservice.exception.EmployeeNotFoundException;
import com.example.commonservice.model.Employee;
import com.example.commonservice.model.EmployeeCourse;
import com.example.commonservice.model.enums.CourseType;
import com.example.commonservice.repository.CourseRepository;
import com.example.commonservice.repository.EmployeeCourseRepository;
import com.example.commonservice.repository.EmployeeRepository;
import com.example.commonservice.service.EmployeeCourseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeCourseServiceImpl implements EmployeeCourseService {
    private static final String EMPLOYEE_NOT_FOUND = "Сотрудник не найден!";
    private static final String COURSE_NOT_FOUND = "Дисциплина не найдена!";
    private static final String EMPLOYEE_COURSE_NOT_FOUND = "Дисциплины, назначенной преподавателю, не найдено!";
    private final ModelMapper modelMapper;
    private final EmployeeRepository employeeRepository;
    private final CourseRepository courseRepository;
    private final EmployeeCourseRepository employeeCourseRepository;

    @Override
    public AddEmployeeCoursesResponse addEmployeeCourses(long employeeId, AddEmployeeCoursesRequest employeeCourseRequests) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND));
        List<EmployeeCourse> employeeCourses = new ArrayList<>();
        for (EmployeeCourseRequest employeeCourseRequest : employeeCourseRequests.getEmployeeCourseRequests()) {
            EmployeeCourse employeeCourse = mapEmployeeCourseRequestToEmployeeCourse(employeeCourseRequest);
            employeeCourse.setEmployee(employee);
            employeeCourse.setCourseType(CourseType.getByName(employeeCourseRequest.getCourseType()));
            employeeCourse.setCourse(courseRepository.findById(employeeCourseRequest.getCourseId())
                    .orElseThrow(() -> new CourseNotFoundException(COURSE_NOT_FOUND)));
            employeeCourses.add(employeeCourse);
        }
        employeeCourses = employeeCourseRepository.saveAll(employeeCourses);
        return AddEmployeeCoursesResponse.builder()
                .employeeCourseResponses(employeeCourses.stream()
                        .map(this::mapEmployeeCourseToEmployeeCourseResponse)
                        .toList())
                .build();
    }

    @Override
    public void deleteEmployeeCourse(long employeeId, long id) {
        employeeCourseRepository.findById(id)
                .ifPresentOrElse(
                        employeeCourseRepository::delete,
                        () -> {
                            throw new EmployeeCourseNotFoundException(EMPLOYEE_COURSE_NOT_FOUND);
                        });
    }

    @Override
    public AddEmployeeCoursesResponse getEmployeeCourses(long employeeId) {
        List<EmployeeCourse> employeeCourses = employeeCourseRepository.findAllByEmployeeId(employeeId);
        return AddEmployeeCoursesResponse.builder()
                .employeeCourseResponses(employeeCourses.stream()
                        .map(this::mapEmployeeCourseToEmployeeCourseResponse)
                        .toList())
                .build();
    }


    private EmployeeCourse mapEmployeeCourseRequestToEmployeeCourse(EmployeeCourseRequest employeeCourseRequest) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper.map(employeeCourseRequest, EmployeeCourse.class);
    }

    private EmployeeCourseResponse mapEmployeeCourseToEmployeeCourseResponse(EmployeeCourse employeeCourse) {
        return modelMapper.map(employeeCourse, EmployeeCourseResponse.class);
    }
}
