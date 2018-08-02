package com.examhelper.app.listener;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.examhelper.app.R;
import com.examhelper.app.constant.HttpConstant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/*
* 注册选择器
*
* */
public class RegisterChooseListener implements View.OnClickListener {

    private  Context context;
    private String[] lists={"程序员","网络管理员","多媒体应用制作技术员","电子商务技术员","信息系统运行管理员","网页制作员","信息处理技术员"};
    private TextView choose;
    private RequestQueue mRequsetQueue;
    public static List<String> nameList;
    private static final String TAG = "RegisterChooseListener";
    public  RegisterChooseListener(Context context, TextView choose){
        this.context=context;
        this.choose=choose;
        initData();

    }
    private void initData() {
        mRequsetQueue= Volley.newRequestQueue(context);
        nameList=new ArrayList<String>();
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, HttpConstant.API_CERTIFICATIONS, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "onResponse: "+response.toString());
                try {
                    JSONObject jsonObject=new JSONObject(response.toString());
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        nameList.add(jsonArray.getJSONObject(i).getString("name"));
                        Log.e(TAG, "onResponse: "+ jsonArray.getJSONObject(i).getString("name"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: "+error.toString());
                Toast.makeText(context, "无法获取当前科目", Toast.LENGTH_SHORT).show();
            }
        });
        mRequsetQueue.add(jsonObjectRequest);
        mRequsetQueue.start();
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
