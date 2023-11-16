package com.example.commonservice.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseRequest {
    private String name;
    private String abbreviation;
}