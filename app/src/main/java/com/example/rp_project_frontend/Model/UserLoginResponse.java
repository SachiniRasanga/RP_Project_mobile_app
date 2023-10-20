package com.example.rp_project_frontend.Model;

public class UserLoginResponse {
    private Boolean success;
    private String accessToken;
    private String email;
    private String userId;
    private String message;
    private Integer roleId;

    public UserLoginResponse(Boolean success, String accessToken, String email, String userId, String message, Integer roleId) {
        this.success = success;
        this.accessToken = accessToken;
        this.email = email;
        this.userId = userId;
        this.message = message;
        this.roleId = roleId;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
