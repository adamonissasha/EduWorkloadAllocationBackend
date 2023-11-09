package com.example.commonservice.model.enums;

import lombok.Getter;

@Getter
public enum JobTitle {
    ASSISTANT (""),
    TEACHER(""),
    SENIOR_TEACHER(""),
    ASSISTANT_PROFESSOR(""),
    PROFESSOR(""),
    HEAD_OF_DEPARTMENT(""),
    DEAN(""),
    DEPUTY_DEAN_FOR_ACADEMIC_AFFAIRS(""),
    DEPUTY_DEAN_FOR_EDUCATIONAL_WORK(""),
    RECTOR(""),
    VICE_RECTOR("");

    private final String name;

    JobTitle(String name) {
        this.name = name;
    }

    public static JobTitle getByName(String name) {
        for(JobTitle jobTitle : JobTitle.values()) {
            if(name.equals(jobTitle.getName())) {
                return jobTitle;
            }
        }
        return JobTitle.TEACHER;
    }

}
