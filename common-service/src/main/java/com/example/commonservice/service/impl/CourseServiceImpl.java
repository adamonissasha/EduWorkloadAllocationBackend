package com.example.commonservice.service.impl;

import com.example.commonservice.dto.request.CourseRequest;
import com.example.commonservice.dto.response.CourseResponse;
import com.example.commonservice.exception.CourseNotFoundException;
import com.example.commonservice.model.Course;
import com.example.commonservice.repository.CourseRepository;
import com.example.commonservice.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private static final String COURSE_NOT_FOUND = "Дисциплина не найдена!";
    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;

    @Override
    public CourseResponse createCourse(CourseRequest courseRequest) {
        Course newCourse = mapCourseRequestToCourse(courseRequest);
        newCourse = courseRepository.save(newCourse);
        return mapCourseToCourseResponse(newCourse);
    }

    @Override
    public CourseResponse editCourse(long id, CourseRequest courseRequest) {
        Course updatedCourse = mapCourseRequestToCourse(courseRequest);
        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(COURSE_NOT_FOUND));
        updatedCourse.setId(existingCourse.getId());
        updatedCourse = courseRepository.save(updatedCourse);
        return mapCourseToCourseResponse(updatedCourse);
    }

    @Override
    public void deleteCourse(long id) {
        courseRepository.findById(id)
                .ifPresentOrElse(
                        course -> courseRepository.deleteById(id),
                        () -> {
                            throw new CourseNotFoundException(COURSE_NOT_FOUND);
                        }
                );
    }

    @Override
    public List<CourseResponse> getCourses() {
        return courseRepository.findAll()
                .stream()
                .map(this::mapCourseToCourseResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CourseResponse getCourseById(long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(COURSE_NOT_FOUND));
        return mapCourseToCourseResponse(course);
    }


    private Course mapCourseRequestToCourse(CourseRequest courseRequest) {
        return modelMapper.map(courseRequest, Course.class);
    }

    private CourseResponse mapCourseToCourseResponse(Course course) {
        return modelMapper.map(course, CourseResponse.class);
    }
}
