package com.examhelper.app.listener;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.examhelper.app.R;

/*
* 注册选择器
*
* */
public class RegisterChooseListener implements View.OnClickListener {

    private  Context context;
    private String[] lists={"程序员","网络管理员","多媒体应用制作技术员","电子商务技术员","信息系统运行管理员","网页制作员","信息处理技术员"};
    private TextView choose;
    public  RegisterChooseListener(Context context, TextView choose){
        this.context=context;
        this.choose=choose;
    }
    @Override
    public void onClick(View v) {
        final Dialog dialog=new Dialog(context);
        View view = View.inflate(context, R.layout.dialog_choose, null);
        ListView lv_choose = view.findViewById(R.id.lv_choose);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        lv_choose.setAdapter(new ArrayAdapter(context,R.layout.item_tv,lists));
        lv_choose.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                choose.setText(lists[position]);
                dialog.dismiss();
            }
        });
        dialog.setContentView(view);
        dialog.show();
    }
}
