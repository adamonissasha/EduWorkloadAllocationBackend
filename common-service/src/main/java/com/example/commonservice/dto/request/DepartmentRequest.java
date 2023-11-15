package com.example.commonservice.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepartmentRequest {
    private String name;
    private String abbreviation;
    private String phone;
}