
/*
package com.example.splash;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    final static  private String URL="http://192.168.35.41:3000/signin";
    private Map<String,String> map;

    public RegisterRequest(String userID, String userPassword, String userNick, String userName, String userBirth, Response.Listener<String>listener){
        super(Method.POST,URL,listener,null);//위 url에 post방식으로 값을 전송

        map=new HashMap<>();
        map.put("id",userID);
        map.put("pw",userPassword);
        map.put("nickName", userNick);
        map.put("name",userName);
        map.put("birth",userBirth);

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
*/