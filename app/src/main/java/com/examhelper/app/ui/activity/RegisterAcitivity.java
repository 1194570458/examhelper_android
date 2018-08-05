package com.examhelper.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.examhelper.app.R;
import com.examhelper.app.constant.IntentFlagConstant;
import com.examhelper.app.listener.RegisterChooseListener;
import com.examhelper.app.listener.RegisterListener;
import com.examhelper.app.messageevent.RegisterEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;

public class RegisterAcitivity extends BaseActivity {

    private RegisterAcitivity registerAcitivity;
    private TextInputEditText etUsername;
    private TextInputLayout register_number;
    private TextInputEditText register_firstPassword;
    private TextInputLayout til_register_firstPassword;
    private TextInputEditText register_confirmPassword;
    private TextInputLayout til_register_confirmPassword;
    private TextView choose;
    private RelativeLayout rl_choose;
    private Button btn_register;
    private static final String TAG = "RegisterAcitivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_register);
        registerAcitivity = this;
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
        btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new RegisterListener(registerAcitivity, etUsername, register_firstPassword, register_confirmPassword, choose));
        rl_choose.setOnClickListener(new RegisterChooseListener(this, choose));
    }

    //注册回调方法
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void registerCallBack(RegisterEvent registerEvent) {
        Log.d("LoginActivity", "loginEvent:" + registerEvent);
        if (registerEvent.getType() == 0) {
            Intent intent = new Intent();
            intent.putExtra(IntentFlagConstant.REGISTER_BACK_USER, (Serializable) registerEvent.getUser());
            startActivity(intent);
            RegisterAcitivity.this.finish();
        } else {
            Toast.makeText(registerAcitivity, "注册失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}