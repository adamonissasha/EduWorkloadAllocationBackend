package com.example.commonservice.service;

import com.example.commonservice.dto.request.FacultyRequest;
import com.example.commonservice.dto.response.FacultyResponse;

import java.util.List;

public interface FacultyService {
    FacultyResponse createFaculty(FacultyRequest facultyRequest);

    FacultyResponse editFaculty(long id, FacultyRequest facultyRequest);

    void deleteFaculty(long id);

    List<FacultyResponse> getFaculties();

    FacultyResponse getFacultyById(long id);
}
