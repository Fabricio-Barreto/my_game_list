package com.plancto.mygamelist.enums;

public enum RoleName {
    ADMIN("admin"),
    USER("user");

    private String role;

    RoleName(String role) {
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
