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
import com.examhelper.app.entity.Certification;
import com.examhelper.app.service.ICertificatesService;
import com.examhelper.app.service.imp.CertificatesServiceImp;

import java.util.List;

/*
* 注册选择器
*
* */
public class RegisterChooseListener implements View.OnClickListener {

    private  Context context;
    private TextView choose;
    private ICertificatesService certificatesService;
    public static List<String> nameList;
    private static final String TAG = "RegisterChooseListener";
    public  RegisterChooseListener(Context context, TextView choose){
        this.context=context;
        this.choose=choose;
        initData();

    }
    private void initData() {
        certificatesService=new CertificatesServiceImp(context);
        for (Certification certification : certificatesService.queryCertificates()) {
            String cerName = certification.getCerName();
            nameList.add(cerName);
        }
    }

    @Override
    public void onClick(View v) {
        final Dialog dialog=new Dialog(context);
        View view = View.inflate(context, R.layout.dialog_choose, null);
        ListView lv_choose = view.findViewById(R.id.lv_choose);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        lv_choose.setAdapter(new ArrayAdapter(context,R.layout.item_tv,lists));
        lv_choose.setAdapter(new ArrayAdapter(context,R.layout.item_tv,nameList));

        lv_choose.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    choose.setText(nameList.get(position));
                dialog.dismiss();
            }
        });
        dialog.setContentView(view);
        dialog.show();
    }
}
