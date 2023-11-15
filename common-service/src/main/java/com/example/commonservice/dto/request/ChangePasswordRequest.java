package com.example.commonservice.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordRequest {
    public String password;
    public String newPassword;
}
