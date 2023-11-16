package com.example.commonservice.controller;

import com.example.commonservice.dto.request.SpecialityCourseRequest;
import com.example.commonservice.dto.response.AddSpecialityCoursesResponse;
import com.example.commonservice.service.SpecialityCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/common/course/speciality/{specialityId}")
@RequiredArgsConstructor
public class SpecialityCourseController {
    private final SpecialityCourseService specialityCourseService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public AddSpecialityCoursesResponse addSpecialityCourses(@PathVariable("specialityId") long specialityId,
                                                             @RequestBody SpecialityCourseRequest specialityCourseRequest) {
        return specialityCourseService.addSpecialityCourses(specialityId, specialityCourseRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSpecialityCourse(@PathVariable("specialityId") long specialityId,
                                       @PathVariable("id") long id) {
        specialityCourseService.deleteSpecialityCourse(specialityId, id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public AddSpecialityCoursesResponse getSpecialityCourses(@PathVariable("specialityId") long specialityId) {
        return specialityCourseService.getSpecialityCourses(specialityId);
    }
}
