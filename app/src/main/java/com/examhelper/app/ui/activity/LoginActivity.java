package com.examhelper.app.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.examhelper.app.R;
import com.examhelper.app.listener.LoginToMainListener;
import com.examhelper.app.listener.LoginToRegisterListener;


public class LoginActivity extends Activity {
    private TextInputEditText etUsername;
    private TextInputLayout til_login_user;
    private TextInputEditText etPassword;
    private RelativeLayout tl_login;
    private Button btn_login;
    private TextView tv_forgetPwd;
    private TextView tv_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }
    private void initView() {
        etUsername = (TextInputEditText) findViewById(R.id.etUsername);
        til_login_user = (TextInputLayout) findViewById(R.id.til_login_user);
        etPassword = (TextInputEditText) findViewById(R.id.etPassword);
        tl_login = (RelativeLayout) findViewById(R.id.tl_login);
        btn_login = (Button) findViewById(R.id.btn_login);
        tv_forgetPwd = (TextView) findViewById(R.id.tv_forgetPwd);
        tv_register = (TextView) findViewById(R.id.tv_register);
        tv_register.setOnClickListener(new LoginToRegisterListener(this));
        btn_login.setOnClickListener(new LoginToMainListener(this));
        tv_forgetPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "暂无此功能", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
