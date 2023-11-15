package com.example.commonservice.service.impl;

import com.example.commonservice.dto.request.FacultyRequest;
import com.example.commonservice.dto.response.FacultyResponse;
import com.example.commonservice.exception.FacultyNotFoundException;
import com.example.commonservice.model.Faculty;
import com.example.commonservice.repository.FacultyRepository;
import com.example.commonservice.service.FacultyService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FacultyServiceImpl implements FacultyService {
    private static final String FACULTY_NOT_FOUND = "Факультет не найден!";
    private final FacultyRepository facultyRepository;
    private final ModelMapper modelMapper;

    @Override
    public FacultyResponse createFaculty(FacultyRequest facultyRequest) {
        Faculty newFaculty = mapFacultyRequestToFaculty(facultyRequest);
        newFaculty = facultyRepository.save(newFaculty);
        return mapFacultyToFacultyResponse(newFaculty);
    }

    @Override
    public FacultyResponse editFaculty(long id, FacultyRequest facultyRequest) {
        Faculty updatedFaculty = mapFacultyRequestToFaculty(facultyRequest);
        Faculty existingFaculty = facultyRepository.findById(id)
                .orElseThrow(() -> new FacultyNotFoundException(FACULTY_NOT_FOUND));
        updatedFaculty.setId(existingFaculty.getId());
        updatedFaculty = facultyRepository.save(updatedFaculty);
        return mapFacultyToFacultyResponse(updatedFaculty);
    }

    @Override
    public void deleteFaculty(long id) {
        facultyRepository.findById(id)
                .ifPresentOrElse(
                        faculty -> facultyRepository.deleteById(id),
                        () -> {
                            throw new FacultyNotFoundException(FACULTY_NOT_FOUND);
                        }
                );
    }

    @Override
    public List<FacultyResponse> getFaculties() {
        return facultyRepository.findAll()
                .stream()
                .map(this::mapFacultyToFacultyResponse)
                .collect(Collectors.toList());
    }

    @Override
    public FacultyResponse getFacultyById(long id) {
        Faculty faculty = facultyRepository.findById(id)
                .orElseThrow(() -> new FacultyNotFoundException(FACULTY_NOT_FOUND));
        return mapFacultyToFacultyResponse(faculty);
    }


    private Faculty mapFacultyRequestToFaculty(FacultyRequest facultyRequest) {
        return modelMapper.map(facultyRequest, Faculty.class);
    }

    private FacultyResponse mapFacultyToFacultyResponse(Faculty faculty) {
        return modelMapper.map(faculty, FacultyResponse.class);
    }
}
