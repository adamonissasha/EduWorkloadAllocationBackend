package com.example.commonservice.dto.response;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddSpecialityCoursesResponse {
    private List<SpecialityCourseResponse> specialityCourseResponses;
}