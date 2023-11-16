package com.example.commonservice.service.impl;

import com.example.commonservice.dto.request.SpecialityRequest;
import com.example.commonservice.dto.response.SpecialityResponse;
import com.example.commonservice.exception.DepartmentNotFoundException;
import com.example.commonservice.exception.FacultyNotFoundException;
import com.example.commonservice.exception.SpecialityNotFoundException;
import com.example.commonservice.model.Department;
import com.example.commonservice.model.Faculty;
import com.example.commonservice.model.Speciality;
import com.example.commonservice.repository.DepartmentRepository;
import com.example.commonservice.repository.FacultyRepository;
import com.example.commonservice.repository.SpecialityRepository;
import com.example.commonservice.service.SpecialityService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpecialityServiceImpl implements SpecialityService {
    private static final String SPECIALITY_NOT_FOUND = "Специальность не найдена!";
    private static final String FACULTY_NOT_FOUND = "Факультет не найден!";
    private static final String DEPARTMENT_NOT_FOUND = "Кафедра не найдена!";
    private final SpecialityRepository specialityRepository;
    private final DepartmentRepository departmentRepository;
    private final FacultyRepository facultyRepository;
    private final ModelMapper modelMapper;

    @Override
    public SpecialityResponse createSpeciality(SpecialityRequest specialityRequest) {
        Department department = departmentRepository.findById(specialityRequest.getDepartmentId())
                .orElseThrow(() -> new DepartmentNotFoundException(DEPARTMENT_NOT_FOUND));
        Faculty faculty = facultyRepository.findById(specialityRequest.getFacultyId())
                .orElseThrow(() -> new FacultyNotFoundException(FACULTY_NOT_FOUND));
        Speciality newSpeciality = mapSpecialityRequestToSpeciality(specialityRequest);
        newSpeciality = specialityRepository.save(newSpeciality);
        SpecialityResponse specialityResponse = mapSpecialityToSpecialityResponse(newSpeciality);
        specialityResponse.setDepartmentName(department.getName());
        specialityResponse.setFacultyName(faculty.getName());
        return specialityResponse;
    }

    @Override
    public SpecialityResponse editSpeciality(long id, SpecialityRequest specialityRequest) {
        Department department = departmentRepository.findById(specialityRequest.getDepartmentId())
                .orElseThrow(() -> new DepartmentNotFoundException(DEPARTMENT_NOT_FOUND));
        Faculty faculty = facultyRepository.findById(specialityRequest.getFacultyId())
                .orElseThrow(() -> new FacultyNotFoundException(FACULTY_NOT_FOUND));
        Speciality existingSpeciality = specialityRepository.findById(id)
                .orElseThrow(() -> new SpecialityNotFoundException(SPECIALITY_NOT_FOUND));
        Speciality updatedSpeciality = mapSpecialityRequestToSpeciality(specialityRequest);
        updatedSpeciality.setId(existingSpeciality.getId());
        updatedSpeciality = specialityRepository.save(updatedSpeciality);
        SpecialityResponse specialityResponse = mapSpecialityToSpecialityResponse(updatedSpeciality);
        specialityResponse.setDepartmentName(department.getName());
        specialityResponse.setFacultyName(faculty.getName());
        return specialityResponse;
    }

    @Override
    public void deleteSpeciality(long id) {
        specialityRepository.findById(id)
                .ifPresentOrElse(
                        speciality -> specialityRepository.deleteById(id),
                        () -> {
                            throw new SpecialityNotFoundException(SPECIALITY_NOT_FOUND);
                        }
                );
    }

    @Override
    public List<SpecialityResponse> getSpecialities() {
        return specialityRepository.findAll()
                .stream()
                .map(this::mapSpecialityToSpecialityResponse)
                .collect(Collectors.toList());
    }

    @Override
    public SpecialityResponse getSpecialityById(long id) {
        Speciality speciality = specialityRepository.findById(id)
                .orElseThrow(() -> new SpecialityNotFoundException(SPECIALITY_NOT_FOUND));
        return mapSpecialityToSpecialityResponse(speciality);
    }


    private Speciality mapSpecialityRequestToSpeciality(SpecialityRequest specialityRequest) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper.map(specialityRequest, Speciality.class);
    }

    private SpecialityResponse mapSpecialityToSpecialityResponse(Speciality speciality) {
        return modelMapper.map(speciality, SpecialityResponse.class);
    }
}
