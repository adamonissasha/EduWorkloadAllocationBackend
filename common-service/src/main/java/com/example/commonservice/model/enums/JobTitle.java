package com.example.commonservice.model.enums;

import lombok.Getter;

@Getter
public enum JobTitle {
    ASSISTANT("Ассистент"),
    TEACHER("Преподаватель"),
    SENIOR_TEACHER("Старший преподаватель"),
    ASSISTANT_PROFESSOR("Доцент"),
    PROFESSOR("Профессор"),
    HEAD_OF_DEPARTMENT("Заведеющий кафедры"),
    DEAN("Декан"),
    DEPUTY_DEAN_FOR_ACADEMIC_AFFAIRS("Заместитель декана по научной работе"),
    DEPUTY_DEAN_FOR_EDUCATIONAL_WORK("Заместитель декана по воспитательной работе"),
    RECTOR("Ректор"),
    VICE_RECTOR("Проректор");

    private final String name;

    JobTitle(String name) {
        this.name = name;
    }

    public static JobTitle getByName(String name) {
        for (JobTitle jobTitle : JobTitle.values()) {
            if (name.equals(jobTitle.getName())) {
                return jobTitle;
            }
        }
        return JobTitle.TEACHER;
    }
}