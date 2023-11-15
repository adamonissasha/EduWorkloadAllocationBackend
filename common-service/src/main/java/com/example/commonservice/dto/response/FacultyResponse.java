package com.example.commonservice.dto.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FacultyResponse {
    private long id;
    private String name;
    private String abbreviation;
    private String phone;
}