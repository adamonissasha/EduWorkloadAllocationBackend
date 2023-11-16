package com.example.commonservice.dto.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseResponse {
    private long id;
    private String name;
    private String abbreviation;
}