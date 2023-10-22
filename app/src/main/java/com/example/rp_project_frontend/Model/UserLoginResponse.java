package com.example.rp_project_frontend.Model;

public class UserLoginResponse {
    private String message;
    private String data;
    private String usertype;
    private String userid;

    public UserLoginResponse(String message,String data,String usertype, String userid){
        this.message = message;
        this.data = data;
        this.usertype = usertype;
        this.userid = userid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
