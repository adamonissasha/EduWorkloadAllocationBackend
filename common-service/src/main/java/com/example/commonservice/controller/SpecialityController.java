package com.example.commonservice.controller;

import com.example.commonservice.dto.request.SpecialityRequest;
import com.example.commonservice.dto.response.SpecialityResponse;
import com.example.commonservice.service.SpecialityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/common/speciality")
@RequiredArgsConstructor
public class SpecialityController {
    private final SpecialityService specialityService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public SpecialityResponse createSpeciality(@RequestBody SpecialityRequest specialityRequest) {
        return specialityService.createSpeciality(specialityRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SpecialityResponse editSpeciality(@PathVariable("id") long id,
                                             @RequestBody SpecialityRequest specialityRequest) {
        return specialityService.editSpeciality(id, specialityRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSpeciality(@PathVariable("id") long id) {
        specialityService.deleteSpeciality(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<SpecialityResponse> getSpecialities() {
        return specialityService.getSpecialities();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SpecialityResponse getSpeciality(@PathVariable("id") long id) {
        return specialityService.getSpecialityById(id);
    }
}
