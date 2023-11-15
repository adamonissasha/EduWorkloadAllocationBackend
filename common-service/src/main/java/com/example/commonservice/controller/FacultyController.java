package com.example.commonservice.controller;

import com.example.commonservice.dto.request.FacultyRequest;
import com.example.commonservice.dto.response.FacultyResponse;
import com.example.commonservice.service.FacultyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/common/faculty")
@RequiredArgsConstructor
public class FacultyController {
    private final FacultyService facultyService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public FacultyResponse createFaculty(@RequestBody FacultyRequest facultyRequest) {
        return facultyService.createFaculty(facultyRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FacultyResponse editFaculty(@PathVariable("id") long id,
                                       @RequestBody FacultyRequest facultyRequest) {
        return facultyService.editFaculty(id, facultyRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFaculty(@PathVariable("id") long id) {
        facultyService.deleteFaculty(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<FacultyResponse> getFaculties() {
        return facultyService.getFaculties();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FacultyResponse getFaculty(@PathVariable("id") long id) {
        return facultyService.getFacultyById(id);
    }
}
