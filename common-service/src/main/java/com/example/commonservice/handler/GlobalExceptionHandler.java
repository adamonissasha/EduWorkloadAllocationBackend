package com.example.commonservice.handler;

import com.example.commonservice.exception.DepartmentNotFoundException;
import com.example.commonservice.exception.EmployeeNotFoundException;
import com.example.commonservice.exception.FacultyNotFoundException;
import com.example.commonservice.exception.SpecialityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final String BAD_CREDENTIAL_MESSAGE = "Неверные логин или пароль!";

    @ExceptionHandler(value = BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String badCredentialException() {
        return BAD_CREDENTIAL_MESSAGE;
    }

    @ExceptionHandler(value = EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(value = FacultyNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleFacultyNotFoundException(FacultyNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(value = SpecialityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleSpecialityNotFoundException(SpecialityNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(value = DepartmentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleDepartmentNotFoundException(DepartmentNotFoundException ex) {
        return ex.getMessage();
    }
}