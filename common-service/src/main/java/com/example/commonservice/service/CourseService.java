package com.example.commonservice.service;

import com.example.commonservice.dto.request.CourseRequest;
import com.example.commonservice.dto.response.CourseResponse;

import java.util.List;

public interface CourseService {
    CourseResponse createCourse(CourseRequest courseRequest);

    CourseResponse editCourse(long id, CourseRequest courseRequest);

    void deleteCourse(long id);

    List<CourseResponse> getCourses();

    CourseResponse getCourseById(long id);
}
