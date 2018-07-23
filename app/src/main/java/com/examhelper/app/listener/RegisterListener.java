package com.examhelper.app.listener;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterListener implements View.OnClickListener {
    String photoNumberPattern = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$";
    private TextInputEditText etUsername;
    private TextInputEditText register_firstPassword;
    private TextInputEditText register_confirmPassword;
    private Context context;
private TextView choose;
    public RegisterListener(Context context, TextInputEditText etUsername, TextInputEditText register_firstPassword, TextInputEditText register_confirmPassword, TextView choose) {
        this.etUsername = etUsername;
        this.register_confirmPassword = register_confirmPassword;
        this.register_firstPassword = register_firstPassword;
        this.context = context;
        this.choose=choose;
    }

    @Override
    public void onClick(View v) {
        String etUsernames = etUsername.getText().toString().trim();
        String register_firstPasswords = register_firstPassword.getText().toString().trim();
        String register_confirmPasswords = register_confirmPassword.getText().toString().trim();
        if (etUsernames.equals("")) {
            Toast.makeText(context, "手机号码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (register_firstPasswords.equals("") || register_confirmPasswords.equals("")) {
            Toast.makeText(context, "密码不能为空", Toast.LENGTH_SHORT).show();            return;

        }
        Pattern pattern = Pattern.compile(photoNumberPattern);
        Matcher matcher = pattern.matcher(etUsernames);
        if (!matcher.matches()) {
            Toast.makeText(context, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
            return;

        }
        if (!register_firstPasswords.equals(register_confirmPasswords)) {
            Toast.makeText(context, "密码不一样,请再次尝试", Toast.LENGTH_SHORT).show();
            return;

        }
        if (choose.getText().toString().trim().equals("请选择你的科目")){
            Toast.makeText(context, "你还没选择科目", Toast.LENGTH_SHORT).show();
            return;
        }

    }
}
