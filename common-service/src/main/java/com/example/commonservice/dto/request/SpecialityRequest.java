package com.example.commonservice.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SpecialityRequest {
    private String name;
    private String abbreviation;
    private String code;
    private Long departmentId;
    private Long facultyId;
}