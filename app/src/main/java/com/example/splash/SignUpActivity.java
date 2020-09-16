package com.example.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.splash.data.JoinData;
import com.example.splash.data.JoinResponse;
import com.example.splash.network.RetrofitClient;
import com.example.splash.network.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;

public class SignUpActivity extends Activity {

    private ImageButton btnSignUp;
    private EditText userName;
    private EditText userEmail;
    private EditText userPwd;
    private EditText userConfirm;
    private ServiceApi service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        userName = (EditText) findViewById(R.id.signName);
        userEmail = (EditText) findViewById(R.id.signEmail);
        userPwd = (EditText) findViewById(R.id.signPwd);
        userConfirm = (EditText) findViewById(R.id.signConfirm);
        btnSignUp = (ImageButton) findViewById(R.id.BtnSignUp);

        service = RetrofitClient.getClient().create(ServiceApi.class);

        btnSignUp.setOnClickListener(view -> {
            btnSignUp.setBackgroundResource(R.drawable.btn_signup_success);
            attemptJoin();
        });
    }
    private void attemptJoin() {
        userName.setError(null);
        userEmail.setError(null);
        userPwd.setError(null);

        String name = userName.getText().toString();
        String email = userEmail.getText().toString();
        String password = userPwd.getText().toString();
        String nickName = userName.getText().toString();
        String birth = "2020.08.11";

        boolean cancel = false;
        View focusView = null;

        // 패스워드의 유효성 검사
        if (password.isEmpty()) {
            userName.setError("비밀번호를 입력해주세요.");
            focusView =userName;
            cancel = true;
        } else if (!isPasswordValid(password)) {
            userPwd.setError("6자 이상의 비밀번호를 입력해주세요.");
            focusView = userPwd;
            cancel = true;
        }

        // 이메일의 유효성 검사
        if (email.isEmpty()) {
            userEmail.setError("이메일을 입력해주세요.");
            focusView = userEmail;
            cancel = true;
        } else if (!isEmailValid(email)) {
            userEmail.setError("@를 포함한 유효한 이메일을 입력해주세요.");
            focusView = userEmail;
            cancel = true;
        }

        // 이름의 유효성 검사
        if (name.isEmpty()) {
            userName.setError("이름을 입력해주세요.");
            focusView = userName;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            startJoin(new JoinData(email, password, nickName, name, birth));
        }

    }

    private void startJoin(JoinData data) {
        service.userJoin(data).enqueue(new Callback<JoinResponse>() {
            @Override
            public void onResponse(Call<JoinResponse> call, retrofit2.Response<JoinResponse> response) {
                JoinResponse result = response.body();

                if (result.getResult()) {

                    Intent titleIntent = new Intent(SignUpActivity.this, LoginActivity.class);
                    SignUpActivity.this.startActivity(titleIntent);
                    finish();
                }


            }

            @Override
            public void onFailure(Call<JoinResponse> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, "회원가입 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("회원가입 에러 발생", t.getMessage());
            }
        });
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 6;
    }
}