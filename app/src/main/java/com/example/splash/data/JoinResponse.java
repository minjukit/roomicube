
package com.example.splash.data;

import com.google.gson.annotations.SerializedName;

public class JoinResponse {

@SerializedName("sucess")
    private boolean result;

    public boolean getResult(){
        return result;
    }

}