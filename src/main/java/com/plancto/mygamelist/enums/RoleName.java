package com.plancto.mygamelist.enums;

public enum RoleName {
    ROLE_ADMIN("admin"),
    ROLE_USER("user");

    private String role;

    RoleName(String role) {
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
