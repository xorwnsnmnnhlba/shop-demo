package com.example.shopdemo.models;

public enum Role {
    ROLE_USER("ROLE_USER"), ADMIN_USER("ROLE_ADMIN");

    private final String value;

    Role(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }

}
