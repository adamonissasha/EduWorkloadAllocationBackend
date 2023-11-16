package com.example.commonservice.controller;

import com.example.commonservice.dto.request.CourseRequest;
import com.example.commonservice.dto.response.CourseResponse;
import com.example.commonservice.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/common/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CourseResponse createCourse(@RequestBody CourseRequest courseRequest) {
        return courseService.createCourse(courseRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CourseResponse editCourse(@PathVariable("id") long id,
                                     @RequestBody CourseRequest courseRequest) {
        return courseService.editCourse(id, courseRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCourse(@PathVariable("id") long id) {
        courseService.deleteCourse(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<CourseResponse> getCourses() {
        return courseService.getCourses();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CourseResponse getCourse(@PathVariable("id") long id) {
        return courseService.getCourseById(id);
    }
}
