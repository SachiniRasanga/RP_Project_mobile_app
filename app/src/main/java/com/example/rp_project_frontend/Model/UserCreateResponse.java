package com.example.rp_project_frontend.Model;

import org.json.JSONObject;

public class UserCreateResponse {
    private String message;
    private JSONObject data;

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

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }
}
