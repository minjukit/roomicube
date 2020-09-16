package com.example.splash.data;

import com.google.gson.annotations.SerializedName;

public class LoginData {
    @SerializedName("id")
    String userEmail;

    @SerializedName("pw")
    String userPwd;

    public LoginData(String userEmail, String userPwd) {
        this.userEmail = userEmail;
        this.userPwd = userPwd;
    }
}
