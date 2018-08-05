package com.examhelper.app.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.examhelper.app.R;
import com.examhelper.app.adapter.CollectionsShowAdapter;
import com.examhelper.app.adapter.WrongShowAdapter;
import com.examhelper.app.constant.IntentFlagConstant;
import com.examhelper.app.constant.NormalConstant;
import com.examhelper.app.entity.Question;
import com.examhelper.app.service.imp.QuesionServiceImp;

import java.util.List;

public class WrongAndCollectionsActivity extends BaseActivity implements View.OnClickListener{
    private ListView lv_wrongbook;
    private List<Question> questions;
    private TextView tv_title;
    private String title;
    private ImageView left;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrongbook);
        initData();
        initView();
    }

    private void initData() {
        questions = (List<Question>) getIntent().getSerializableExtra(IntentFlagConstant.GET_WRONG_QUESTIONS);
        //test
        title=getIntent().getStringExtra(IntentFlagConstant.IS_WRONGORCOLLECT);
        lv_wrongbook = (ListView) findViewById(R.id.lv_wrongbook);
        if(NormalConstant.WRONG_BOOK.equals(title)){
            //错题本
            QuesionServiceImp quesionServiceImp = new QuesionServiceImp(this);
            questions = quesionServiceImp.queryErrorQuestion();
            lv_wrongbook.setAdapter(new WrongShowAdapter(this, questions));
        }else if(NormalConstant.MY_COLLECTION.equals(title)){
            //我的收藏
            QuesionServiceImp quesionServiceImp = new QuesionServiceImp(this);
            questions = quesionServiceImp.queryCollectedQuestion();
            lv_wrongbook.setAdapter(new CollectionsShowAdapter(this, questions));
        }

    }

    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText(title);
        left = (ImageView) findViewById(R.id.left);
        left.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.finish();
    }
}
