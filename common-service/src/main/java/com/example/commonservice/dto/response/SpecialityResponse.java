package com.example.commonservice.dto.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SpecialityResponse {
    private Long id;
    private String name;
    private String abbreviation;
    private String code;
    private String departmentName;
    private String facultyName;
}