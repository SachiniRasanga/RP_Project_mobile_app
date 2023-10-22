package com.example.rp_project_frontend.Model;

import java.util.Date;

public class ApplicationUser {
    private String fullname;
    private String username;
    private String email;
    private String password;
    private String mobileno;
    private String userType;
    private Date createdAt;

    public ApplicationUser(String fullname, String username, String email, String password, String mobileno, String userType, Date createdAt) {
        this.fullname = fullname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.mobileno = mobileno;
        this.userType = userType;
        this.createdAt = createdAt;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}