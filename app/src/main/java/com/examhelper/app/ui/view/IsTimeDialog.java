package com.examhelper.app.ui.view;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.examhelper.app.R;
import com.examhelper.app.constant.EventBusMessageConstant;
import com.examhelper.app.messageevent.IsTimeShowEvent;
import com.examhelper.app.ui.activity.AnalogyExaminationActivity;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Administrator on 2018/7/27.
 */

public class IsTimeDialog extends Dialog implements View.OnClickListener {
    private int type;
    private Context context;

    public IsTimeDialog(@NonNull Context context, int type) {
        super(context, R.style.dialog);
        this.context = context;
        this.type = type;
        setContentView(R.layout.my_dialog);
        initView(type);
    }

    private void initView(int type) {
        TextView title = (TextView) findViewById(R.id.dialog_title);
        TextView content = (TextView) findViewById(R.id.dialog_content);
        Button confirm_btn = (Button) findViewById(R.id.dialog_sure);
        Button cancel_btn = (Button) findViewById(R.id.dialog_cancle);
        if (type == IsTimeShowEvent.IS_TIME) {
            content.setText("您的答题时间结束,是否提交试卷?");
            confirm_btn.setText("提交");
            cancel_btn.setText("退出");
        } else if (type == IsTimeShowEvent.IS_END) {
            content.setText("您要结束本次模拟答题吗？");
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
                if (type == IsTimeShowEvent.IS_TIME) {
                    dismiss();
                } else {
                    dismiss();
                }
                break;
            }
            case R.id.dialog_sure: {
                if (type == IsTimeShowEvent.IS_TIME) {
                    IsTimeDialog.this.dismiss();
                    // 统计测试
                    EventBus.getDefault().post(EventBusMessageConstant.COUNTING_SCORE);
                } else {
                    IsTimeDialog.this.dismiss();
                    ((AnalogyExaminationActivity) context).finish();
                }
                break;
            }
        }
    }
}
