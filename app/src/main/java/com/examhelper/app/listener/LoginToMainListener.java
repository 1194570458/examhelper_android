package com.examhelper.app.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.examhelper.app.activity.MainActivity;


public class LoginToMainListener implements View.OnClickListener {
    private  Context context;
    public LoginToMainListener(Context context) {
        this.context=context;
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }
}
