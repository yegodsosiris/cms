package com.rdfgroup.security.domain;

public enum Role {
    User("User"),
    Author("Author"),
    Admin("Admin");
    
    private final String role;
    
    private Role(String role) {
        this.role = role;
    }

    @Override 
    public String toString() {
        return role;
    }
    
}
