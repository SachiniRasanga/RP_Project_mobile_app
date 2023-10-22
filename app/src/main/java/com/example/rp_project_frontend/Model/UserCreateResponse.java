package com.example.rp_project_frontend.Model;

import org.json.JSONObject;

public class UserCreateResponse {
    private String message;
    private Object data;

    public UserCreateResponse(String message, JSONObject data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
