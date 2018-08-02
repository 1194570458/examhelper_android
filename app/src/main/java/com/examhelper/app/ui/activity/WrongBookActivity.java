package com.examhelper.app.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import com.examhelper.app.R;
import com.examhelper.app.adapter.WrongShowAdapter;
import com.examhelper.app.constant.IntentFlagConstant;
import com.examhelper.app.entity.Question;

import java.util.List;

public class WrongBookActivity extends Activity {
    private ListView lv_wrongbook;
    private List<Question> questions;

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
//        QuesionServiceImp quesionServiceImp=new QuesionServiceImp(this);
//         questions = quesionServiceImp.queryErrorQuestion();
    }

    private void initView() {
        lv_wrongbook = (ListView) findViewById(R.id.lv_wrongbook);
        lv_wrongbook.setAdapter(new WrongShowAdapter(this, questions));

    }
}
