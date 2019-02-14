package com.musicalcoder.authentication.network;

import com.musicalcoder.authentication.models.RegisterUser;
import com.musicalcoder.authentication.models.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthAPI {

    @POST("auth/register")
    Call<UserResponse> registerUser(@Body RegisterUser registerUser);
}
