package com.example.commonservice.service;

import com.example.commonservice.dto.request.SpecialityRequest;
import com.example.commonservice.dto.response.SpecialityResponse;

import java.util.List;

public interface SpecialityService {
    SpecialityResponse createSpeciality(SpecialityRequest specialityRequest);

    SpecialityResponse editSpeciality(long id, SpecialityRequest specialityRequest);

    void deleteSpeciality(long id);

    List<SpecialityResponse> getSpecialities();

    SpecialityResponse getSpecialityById(long id);
}
