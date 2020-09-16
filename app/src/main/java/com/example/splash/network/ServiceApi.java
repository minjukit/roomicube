
package com.example.splash.network;

import com.example.splash.data.JoinData;
import com.example.splash.data.JoinResponse;
import com.example.splash.data.LoginData;
import com.example.splash.data.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ServiceApi {

    @POST("/signin")
    Call<JoinResponse> userJoin(@Body JoinData data);

    @POST("/login")
    Call<LoginResponse> userLogin(@Body LoginData data);

}
