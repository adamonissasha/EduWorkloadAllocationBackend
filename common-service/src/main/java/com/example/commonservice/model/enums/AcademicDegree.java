package com.example.commonservice.model.enums;

import lombok.Getter;

@Getter
public enum AcademicDegree {
    DOCTOR_OF_SCIENCES("Доктора наук"),
    CANDIDATE_OF_SCIENCES("Кандидат наук");

    private final String name;

    AcademicDegree(String name) {
        this.name = name;
    }

    public static AcademicDegree getByName(String name) {
        if (name.equals(DOCTOR_OF_SCIENCES.getName())) {
            return AcademicDegree.DOCTOR_OF_SCIENCES;
        } else {
            return AcademicDegree.CANDIDATE_OF_SCIENCES;
        }
    }
}
