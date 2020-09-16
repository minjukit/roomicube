package com.example.splash;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.splash.data.LoginData;
import com.example.splash.data.LoginResponse;
import com.example.splash.network.RetrofitClient;
import com.example.splash.network.ServiceApi;
import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.kakao.auth.AuthType;
import com.kakao.auth.Session;

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;

public class LoginActivity extends Activity implements View.OnClickListener {

    int result1 = 0;
    private ServiceApi service;
    private LoginCallback mLoginCallback;     //facebook
    private CallbackManager mCallbackManager; //facebook
    private SessionCallback sessionCallback = new SessionCallback();
    ; //kakaotalk
    Session session;
    private ImageButton btnKakao;
    private ImageButton btnFacebook;
    private SignInButton btnGoogle;
    private GoogleSignInClient mGoogleSignInClient;
    private static final String TAG = "Oauth2Google";
    private static final int RC_SIGN_IN = 9001;
    private ImageView checkIdIm;
    private ImageView checkPwdIm;
    private EditText email;
    private EditText password;
    TextView textSignUp;
    Window window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        service = RetrofitClient.getClient().create(ServiceApi.class);
        mCallbackManager = CallbackManager.Factory.create();
        mLoginCallback = new LoginCallback();

        textSignUp = (TextView) findViewById(R.id.TextSignUp);

        textSignUp.setPaintFlags(textSignUp.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);  //밑줄

        email = (EditText) findViewById(R.id.TextEmail);

        password = (EditText) findViewById(R.id.TextPassword);

        ImageButton btnSignIn = (ImageButton) findViewById(R.id.BtnSignIn);

        btnSignIn.setOnClickListener(view -> {
            btnSignIn.setBackgroundResource(R.drawable.btn_signin_success);
            attemptLogin();
        });
        //facebook login
        btnFacebook = (ImageButton) findViewById(R.id.BtnFacebook);
        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LoginManager loginManager = LoginManager.getInstance();
                loginManager.logInWithReadPermissions(LoginActivity.this,
                        Arrays.asList("public_profile", "email"));
                loginManager.registerCallback(mCallbackManager, mLoginCallback);
            }
        });

        //kakao
        btnKakao = (ImageButton) findViewById(R.id.BtnKakao);
        session = Session.getCurrentSession();
        session.addCallback(sessionCallback);

        btnKakao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session.open(AuthType.KAKAO_LOGIN_ALL, LoginActivity.this);
            }
        });

        //google
        btnGoogle = (SignInButton) findViewById(R.id.BtnGoogle);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        btnGoogle.setOnClickListener(this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //facebook
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        // kakao sdk에게 전달
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    //google
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }

    private void updateUI(GoogleSignInAccount account) {
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.BtnGoogle:
                signIn();
                break;
            // ...
        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);
    }

    //kakao
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 세션 콜백 삭제
        Session.getCurrentSession().removeCallback(sessionCallback);
    }

    public void onTextViewClick(View view) {  //textView->SignUpActivity
        Intent titleIntent = new Intent(LoginActivity.this, SignUpActivity.class);
        LoginActivity.this.startActivity(titleIntent);
    }

    //login server
    private void attemptLogin() {
        email.setError(null);
        password.setError(null);

        String id = email.getText().toString();
        String pw = password.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // 패스워드의 유효성 검사
        if (pw.isEmpty()) {
            email.setError("비밀번호를 입력해주세요.");
            focusView = email;
            cancel = true;
        } else if (!isPasswordValid(pw)) {
            password.setError("6자 이상의 비밀번호를 입력해주세요.");
            focusView = password;
            cancel = true;
        }

        // 이메일의 유효성 검사
        if (id.isEmpty()) {
            email.setError("이메일을 입력해주세요.");
            focusView = email;
            cancel = true;
        } else if (!isEmailValid(id)) {
            email.setError("@를 포함한 유효한 이메일을 입력해주세요.");
            focusView = email;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            startLogin(new LoginData(id, pw));
        }
    }

    private void startLogin(LoginData data) {
        service.userLogin(data).enqueue(new Callback<LoginResponse>() {

            @Override
            public void onResponse(Call<LoginResponse> call, retrofit2.Response<LoginResponse> response) {
                LoginResponse result = response.body();

                if (result.getResult()) {
                    Intent titleIntent = new Intent(LoginActivity.this, FragActivity.class);
                    LoginActivity.this.startActivity(titleIntent);
                    result1 = 100;
                    finish();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "로그인 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("로그인 에러 발생", t.getMessage());

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