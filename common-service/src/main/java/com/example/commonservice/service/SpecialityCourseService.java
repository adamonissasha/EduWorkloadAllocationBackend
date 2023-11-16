package com.example.commonservice.service;

import com.example.commonservice.dto.request.SpecialityCourseRequest;
import com.example.commonservice.dto.response.AddSpecialityCoursesResponse;

public interface SpecialityCourseService {
    AddSpecialityCoursesResponse addSpecialityCourses(long specialityId, SpecialityCourseRequest specialityCourseRequest);

    void deleteSpecialityCourse(long specialityId, long id);

    AddSpecialityCoursesResponse getSpecialityCourses(long specialityId);
}
