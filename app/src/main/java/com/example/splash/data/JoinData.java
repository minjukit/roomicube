
package com.example.splash.data;


import com.google.gson.annotations.SerializedName;

public class JoinData {
    @SerializedName("id")
    private String userEmail;

    @SerializedName("pw")
    private String userPwd;

    @SerializedName("nickName")
    private String userNick;

    @SerializedName("name")
    private String userName;

    @SerializedName("birth")
    private String userBirth;

    public JoinData(String userEmail, String userPwd, String userNick, String userName, String userBirth) {
        this.userEmail = userEmail;
        this.userPwd = userPwd;
        this.userNick = userNick;
        this.userName = userName;
        this.userBirth = userBirth;
    }
}
