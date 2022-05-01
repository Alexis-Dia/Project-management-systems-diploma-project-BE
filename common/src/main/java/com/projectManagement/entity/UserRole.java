package com.projectManagement.entity;

import java.util.Arrays;

public enum UserRole {

    ADMIN(1, "ADMIN", 1), USER(2, "DRIVER", 2);

    private final Integer id;
    private final String name;
    private final int securityLevel;

    UserRole(Integer id, String name, int securityLevel) {
        this.id = id;
        this.name = name;
        this.securityLevel = securityLevel;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSecurityLevel() {
        return securityLevel;
    }

    public UserRole getRoleByName(String name) {
        return Arrays.stream(values()).filter(ob -> ob.name.equals(name)).findAny().orElseThrow();
    }
}
