package com.example.rp_project_frontend.API;

import com.example.rp_project_frontend.Model.ApplicationUser;
import com.example.rp_project_frontend.Model.UserCreateRequest;
import com.example.rp_project_frontend.Model.UserCreateResponse;
import com.example.rp_project_frontend.Model.UserLoginRequest;
import com.example.rp_project_frontend.Model.UserLoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AuthEndpoints {

    @POST("/user/signup")
    Call<UserCreateResponse> createUser(@Body UserCreateRequest userCreateRequest);

    @POST("/user/signin")
    Call<UserLoginResponse> loginUser(@Body UserLoginRequest userLoginRequest);

    @GET("/user/{id}")
    Call<ApplicationUser> getUserById(@Path("id") String userId);

}
