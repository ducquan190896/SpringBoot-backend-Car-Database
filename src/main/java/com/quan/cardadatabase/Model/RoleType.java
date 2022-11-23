package com.quan.cardadatabase.Model;

public enum RoleType {
    USER("ROLE_USER"),
    MANAGER("ROLE_MANAGER"),
    ADMIN("ROLE_ADMIN");

    private String name;

    RoleType(String name) {
        this.name = name;
    }

    public String getRoleType() {
        return this.name;
    }
    
}
