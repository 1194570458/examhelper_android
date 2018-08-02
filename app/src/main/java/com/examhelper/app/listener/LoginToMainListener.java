package com.examhelper.app.listener;

import android.content.Context;
import android.view.View;

import com.examhelper.app.network.RequestServerHttp;
import com.examhelper.app.ui.activity.LoginActivity;


public class LoginToMainListener implements View.OnClickListener {
    private Context context;
    RequestServerHttp requestServerHttp;

    public LoginToMainListener(Context context) {
        this.context = context;
        requestServerHttp = RequestServerHttp.getInstance(context);
    }


    @Override
    public void onClick(View view) {
        String username = ((LoginActivity) context).etUsername.getText().toString();
        String password = ((LoginActivity) context).etPassword.getText().toString();
        requestServerHttp.requestLogin(username, password);
    }
}

