package com.examhelper.app.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.examhelper.app.R;
import com.examhelper.app.listener.RegisterChooseListener;
import com.examhelper.app.listener.RegisterListener;

public class RegisterAcitivity extends Activity {


    private TextInputEditText etUsername;
    private TextInputLayout register_number;
    private TextInputEditText register_firstPassword;
    private TextInputLayout til_register_firstPassword;
    private TextInputEditText register_confirmPassword;
    private TextInputLayout til_register_confirmPassword;
    private TextView choose;
    private RelativeLayout rl_choose;
    private Button btn_register;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }


    private void initView() {
        etUsername = (TextInputEditText) findViewById(R.id.etUsername);
        register_number = (TextInputLayout) findViewById(R.id.register_number);
        register_firstPassword = (TextInputEditText) findViewById(R.id.register_firstPassword);
        til_register_firstPassword = (TextInputLayout) findViewById(R.id.til_register_firstPassword);
        register_confirmPassword = (TextInputEditText) findViewById(R.id.register_confirmPassword);
        til_register_confirmPassword = (TextInputLayout) findViewById(R.id.til_register_confirmPassword);
        choose = (TextView) findViewById(R.id.choose);
        rl_choose = (RelativeLayout) findViewById(R.id.rl_choose);
        btn_register=findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new RegisterListener(getApplicationContext(),etUsername,register_firstPassword,register_confirmPassword,choose));
        rl_choose.setOnClickListener(new RegisterChooseListener(this,choose));
    }}