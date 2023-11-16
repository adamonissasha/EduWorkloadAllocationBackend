package com.example.commonservice.service.impl;

import com.example.commonservice.dto.request.SpecialityCourseRequest;
import com.example.commonservice.dto.response.AddSpecialityCoursesResponse;
import com.example.commonservice.dto.response.SpecialityCourseResponse;
import com.example.commonservice.exception.CourseNotFoundException;
import com.example.commonservice.exception.SpecialityCourseNotFoundException;
import com.example.commonservice.exception.SpecialityNotFoundException;
import com.example.commonservice.model.Speciality;
import com.example.commonservice.model.SpecialityCourse;
import com.example.commonservice.repository.CourseRepository;
import com.example.commonservice.repository.SpecialityCourseRepository;
import com.example.commonservice.repository.SpecialityRepository;
import com.example.commonservice.service.SpecialityCourseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SpecialityCourseServiceImpl implements SpecialityCourseService {
    private static final String SPECIALITY_NOT_FOUND = "Специальность не найдена!";
    private static final String COURSE_NOT_FOUND = "Дисциплина не найдена!";
    private static final String SPECIALITY_COURSE_NOT_FOUND = "Дисциплины, назначенной специальности, не найдено!";
    private final ModelMapper modelMapper;
    private final SpecialityRepository specialityRepository;
    private final CourseRepository courseRepository;
    private final SpecialityCourseRepository specialityCourseRepository;

    @Override
    public AddSpecialityCoursesResponse addSpecialityCourses(long specialityId, SpecialityCourseRequest specialityCourseRequest) {
        Speciality speciality = specialityRepository.findById(specialityId)
                .orElseThrow(() -> new SpecialityNotFoundException(SPECIALITY_NOT_FOUND));
        List<SpecialityCourse> specialityCourses = new ArrayList<>();
        for (Long courseId : specialityCourseRequest.getCourseIds()) {
            SpecialityCourse specialityCourse = mapSpecialityCourseRequestToSpecialityCourse(specialityCourseRequest);
            specialityCourse.setSpeciality(speciality);
            specialityCourse.setCourse(courseRepository.findById(courseId)
                    .orElseThrow(() -> new CourseNotFoundException(COURSE_NOT_FOUND)));
            specialityCourses.add(specialityCourse);
        }
        specialityCourses = specialityCourseRepository.saveAll(specialityCourses);
        return AddSpecialityCoursesResponse.builder()
                .specialityCourseResponses(specialityCourses.stream()
                        .map(this::mapSpecialityCourseToSpecialityCourseResponse)
                        .toList())
                .build();
    }

    @Override
    public void deleteSpecialityCourse(long specialityId, long id) {
        specialityCourseRepository.findById(id)
                .ifPresentOrElse(
                        specialityCourseRepository::delete,
                        () -> {
                            throw new SpecialityCourseNotFoundException(SPECIALITY_COURSE_NOT_FOUND);
                        });
    }

    @Override
    public AddSpecialityCoursesResponse getSpecialityCourses(long specialityId) {
        List<SpecialityCourse> specialityCourses = specialityCourseRepository.findAllBySpecialityId(specialityId);
        return AddSpecialityCoursesResponse.builder()
                .specialityCourseResponses(specialityCourses.stream()
                        .map(this::mapSpecialityCourseToSpecialityCourseResponse)
                        .toList())
                .build();
    }


    private SpecialityCourse mapSpecialityCourseRequestToSpecialityCourse(SpecialityCourseRequest specialityCourseRequest) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper.map(specialityCourseRequest, SpecialityCourse.class);
    }

    private SpecialityCourseResponse mapSpecialityCourseToSpecialityCourseResponse(SpecialityCourse specialityCourse) {
        return modelMapper.map(specialityCourse, SpecialityCourseResponse.class);
    }
}
