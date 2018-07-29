package com.examhelper.app.ui.view;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.examhelper.app.R;

/**
 * Created by Administrator on 2018/7/27.
 */

public class SubmitDialog extends Dialog{

    public SubmitDialog(@NonNull Context context) {
        super(context, R.style.dialog);
        setContentView(R.layout.my_dialog);
        initView();
    }

    private void initView() {
        TextView title = (TextView) findViewById(R.id.dialog_title);
        TextView content = (TextView) findViewById(R.id.dialog_content);
        content.setText("提交成功，感谢您的参与!");
        final Button confirm_btn = (Button) findViewById(R.id.dialog_sure);
        confirm_btn.setVisibility(View.GONE);
        Button cancel_btn = (Button) findViewById(R.id.dialog_cancle);
        cancel_btn.setVisibility(View.GONE);
        setCanceledOnTouchOutside(true);// 设置点击Dialog外部任意区域关闭Dialog
    }

}
