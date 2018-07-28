package com.examhelper.app.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.examhelper.app.ui.activity.RegisterAcitivity;


public class LoginToRegisterListener implements View.OnClickListener {
    private  Context context;
    public LoginToRegisterListener(Context context) {
        this.context=context;
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(context, RegisterAcitivity.class);
        context.startActivity(intent);
    }
}
