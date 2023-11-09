package com.example.commonservice.model.enums;

import lombok.Getter;

@Getter
public enum Role {
    ROLE_ADMIN ("Администратор локальной сети"),
    ROLE_TEACHER ("Преподаватель"),
    ROLE_DEPARTMENT_WORKER("Работник кафедры");

    private final String name;

    Role(String name) {
        this.name = name;
    }

    public static Role getByName(String name) {
        for(Role role : Role.values()) {
            if(name.equals(role.getName())) {
                return role;
            }
        }
        return Role.ROLE_TEACHER;
    }
}
