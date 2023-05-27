package com.fodala.pojo;

public class Role {
    private Integer id;
    private String username;
    private String role;

    public Role() {

    }

    public Role(Integer id, String username, String role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}