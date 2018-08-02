package com.examhelper.app.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.examhelper.app.R;
import com.examhelper.app.constant.IntentFlagConstant;
import com.examhelper.app.entity.User;
import com.examhelper.app.listener.LoginToMainListener;
import com.examhelper.app.listener.LoginToRegisterListener;
import com.examhelper.app.messageevent.LoginEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class LoginActivity extends Activity {
    private TextInputEditText etUsername;
    private TextInputLayout til_login_user;
    private TextInputEditText etPassword;
    private RelativeLayout tl_login;
    private Button btn_login;
    private TextView tv_forgetPwd;
    private TextView tv_register;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EventBus.getDefault().register(this);
        initData();
        initView();
    }

    private void initData() {
        user = (User) getIntent().getSerializableExtra(IntentFlagConstant.REGISTER_BACK_USER);
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
        if (user != null) {
            etUsername.setText(user.getUsername());
            etPassword.setText(user.getPassword());
        }
    }

    //登陆回调方法
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void loginCallBack(LoginEvent loginEvent) {
        Log.d("LoginActivity", "loginEvent:" + loginEvent);
        if (loginEvent.getType() == 0) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            LoginActivity.this.finish();
        } else {
            Toast.makeText(this, "登陆失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
