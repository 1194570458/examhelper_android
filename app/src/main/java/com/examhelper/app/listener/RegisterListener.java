package com.examhelper.app.listener;

import android.app.Activity;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.examhelper.app.network.RequestServerHttp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterListener implements View.OnClickListener {
    String photoNumberPattern = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$";
    private TextInputEditText etUsername;
    private TextInputEditText register_firstPassword;
    private TextInputEditText register_confirmPassword;
    private Activity activity;
    private TextView choose;
    private RequestQueue mRequsetQueue;
    private static final String TAG = "RegisterListener";

    public RegisterListener(Activity activity, TextInputEditText etUsername, TextInputEditText register_firstPassword, TextInputEditText register_confirmPassword, TextView choose) {
        this.etUsername = etUsername;
        this.register_confirmPassword = register_confirmPassword;
        this.register_firstPassword = register_firstPassword;
        this.activity = activity;
        this.choose = choose;
    }

    @Override
    public void onClick(View v) {
        String etUsernames = etUsername.getText().toString().trim();
        String register_firstPasswords = register_firstPassword.getText().toString().trim();
        String register_confirmPasswords = register_confirmPassword.getText().toString().trim();
        if (etUsernames.equals("")) {
            Toast.makeText(activity, "手机号码不能为空", Toast.LENGTH_LONG).show();
            return;
        }
        if (register_firstPasswords.equals("") || register_confirmPasswords.equals("")) {
            Toast.makeText(activity, "密码不能为空", Toast.LENGTH_LONG).show();
            return;

        }
        Pattern pattern = Pattern.compile(photoNumberPattern);
        Matcher matcher = pattern.matcher(etUsernames);
        if (!matcher.matches()) {
            Toast.makeText(activity, "请输入正确的手机号码", Toast.LENGTH_LONG).show();
            return;

        }
        if (!register_firstPasswords.equals(register_confirmPasswords)) {
            Toast.makeText(activity, "密码不一样,请再次尝试", Toast.LENGTH_LONG).show();
            return;

        }
        if (choose.getText().toString().trim().equals("请选择你的科目")) {
            Toast.makeText(activity, "你还没选择科目", Toast.LENGTH_LONG).show();
            return;
        }
        int carId = 0;
        for (int i = 0; i < RegisterChooseListener.nameList.size(); i++) {
            if (RegisterChooseListener.nameList.get(i).equals(choose.getText().toString().trim())) {
                carId = i + 1;
            }
        }
        //发起注册请求
        String params = "{\"username\":\"%s\",\"password\":\"%s\",\"cerId\":%s}";
        String s = String.format(params, etUsernames, register_firstPasswords, carId);
        RequestServerHttp.getInstance(activity).requestRegister(s);
    }
}
