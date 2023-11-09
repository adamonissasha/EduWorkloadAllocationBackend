package com.example.commonservice.model.enums;

import lombok.Getter;

@Getter
public enum CourseType {
    LECTURE ("Лекция"),
    PRACTICAL_LESSON("Практическое занятие"),
    LABORATORY_LESSON("Лабораторное занятие");

    private final String name;

    CourseType(String name) {
        this.name = name;
    }

    public static CourseType getByName(String name) {
        for(CourseType courseType : CourseType.values()) {
            if(name.equals(courseType.getName())) {
                return courseType;
            }
        }
        return CourseType.LECTURE;
    }
}
