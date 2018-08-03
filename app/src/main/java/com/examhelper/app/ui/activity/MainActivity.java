package com.examhelper.app.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.examhelper.app.R;

public class MainActivity extends AppCompatActivity {

    private ImageView iv_img1;
    private LinearLayout frist_ll;
    private LinearLayout second_ll;
    private LinearLayout third_ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}
