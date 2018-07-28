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

public class IsTimeDialog extends Dialog implements View.OnClickListener {
    private String type;

    public IsTimeDialog(@NonNull Context context, String type) {
        super(context, R.style.dialog);
        this.type = type;
        setContentView(R.layout.my_dialog);
        initView(type);
    }

    private void initView(String type) {
        TextView title = (TextView) findViewById(R.id.dialog_title);
        TextView content = (TextView) findViewById(R.id.dialog_content);
        Button confirm_btn = (Button) findViewById(R.id.dialog_sure);
        Button cancel_btn = (Button) findViewById(R.id.dialog_cancle);
        if (type.equals("0")) {
            content.setText("您的答题时间结束,是否提交试卷?");
        } else if (type.equals("1")) {
            content.setText("您要结束本次模拟答题吗？");
        }
        if (type.equals("0")) {
            confirm_btn.setText("提交");
            cancel_btn.setText("退出");
        } else if (type.equals("1")) {
            confirm_btn.setText("退出");
            cancel_btn.setText("继续答题");
        } else {
            confirm_btn.setText("确定");
            cancel_btn.setVisibility(View.GONE);
        }
        confirm_btn.setOnClickListener(this);
        cancel_btn.setOnClickListener(this);
        setCanceledOnTouchOutside(false);// 设置点击Dialog外部任意区域关闭Dialog
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_cancle: {
                if (type.equals("0")) {
                    dismiss();
                } else {
                    dismiss();
                    //TODO 暂停倒时
                }
                break;
            }
            case R.id.dialog_sure: {
                if (type.equals("0")) {
                    IsTimeDialog.this.dismiss();
                    //TODO 统计测试
//                    uploadExamination(pagerAdapter.errorTopicNum());
                } else {
                    IsTimeDialog.this.dismiss();
                }
                break;
            }
        }
    }
}
