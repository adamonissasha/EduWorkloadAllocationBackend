package com.example.commonservice.dto.request;

import lombok.Data;

@Data
public class NewUserRequest {
    private String username;
    private String password;
}
