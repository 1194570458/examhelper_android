package com.examhelper.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.examhelper.app.R;
import com.examhelper.app.constant.IntentFlagConstant;
import com.examhelper.app.constant.NormalConstant;
import com.examhelper.app.entity.Question;
import com.examhelper.app.service.IQuestionService;
import com.examhelper.app.service.imp.QuesionServiceImp;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout ll_RondomPratice;
    private LinearLayout ll_WrongBook;
    private LinearLayout ll_SequencePractice;
    private LinearLayout ll_SpecialExamination;
    private LinearLayout ll_MyCollection;
    private IQuestionService questionService;
    private LinearLayout ll_simulation_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initData() {
        questionService = new QuesionServiceImp(this);
    }

    private void initView() {
        ll_RondomPratice = (LinearLayout) findViewById(R.id.ll_RondomPratice);
        ll_WrongBook = (LinearLayout) findViewById(R.id.ll_WrongBook);
        ll_SequencePractice = (LinearLayout) findViewById(R.id.ll_SequencePractice);
        ll_SpecialExamination = (LinearLayout) findViewById(R.id.ll_SpecialExamination);
        ll_MyCollection = (LinearLayout) findViewById(R.id.ll_MyCollection);
        ll_RondomPratice.setOnClickListener(this);
        ll_SequencePractice.setOnClickListener(this);
        ll_WrongBook.setOnClickListener(this);
        ll_SpecialExamination.setOnClickListener(this);
        ll_MyCollection.setOnClickListener(this);
        ll_simulation_test =findViewById(R.id.ll_simulation_test);
        ll_simulation_test.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            //顺序练习
            case R.id.ll_SequencePractice:
                intent = new Intent(this, AnalogyExaminationActivity.class);
                List<Question> allQuestions = questionService.queryAllQuestions();
                intent.putExtra(IntentFlagConstant.GET_QUESTIONS, (Serializable) allQuestions);
                intent.putExtra(IntentFlagConstant.PATTERN_TITLE, v.getContext().getResources().getString(R.string.sequence_practice));
                startActivity(intent);
                break;
            //错题本
            case R.id.ll_WrongBook:
                intent = new Intent(this, WrongAndCollectionsActivity.class);
                intent.putExtra(IntentFlagConstant.IS_WRONGORCOLLECT, NormalConstant.WRONG_BOOK);
                startActivity(intent);
                break;
            //我的收藏
            case R.id.ll_MyCollection:
                intent = new Intent(this, WrongAndCollectionsActivity.class);
                intent.putExtra(IntentFlagConstant.IS_WRONGORCOLLECT, NormalConstant.MY_COLLECTION);
                startActivity(intent);
                break;

            //随机练习
            case R.id.ll_RondomPratice:
                intent = new Intent(this, AnalogyExaminationActivity.class);
                List<Question> allRandomQuestion = questionService.queryRandomQuestions();
                intent.putExtra(IntentFlagConstant.GET_QUESTIONS, (Serializable) allRandomQuestion);
                intent.putExtra(IntentFlagConstant.PATTERN_TITLE, v.getContext().getResources().getString(R.string.rondom_pratice));
                startActivity(intent);
                break;
            //专项考试
            case R.id.ll_SpecialExamination:
                intent = new Intent(this, WrongAndCollectionsActivity.class);
                intent.putExtra(IntentFlagConstant.IS_WRONGORCOLLECT, NormalConstant.SPECIAL_EXAMINATION);
                startActivity(intent);
                break;
            //模拟考试
            case R.id.ll_simulation_test:
                intent = new Intent(this, AnalogyExaminationActivity.class);
                allQuestions = questionService.queryAllQuestions();
                intent.putExtra(IntentFlagConstant.GET_QUESTIONS, (Serializable) allQuestions);
                intent.putExtra(IntentFlagConstant.PATTERN_TITLE, v.getContext().getResources().getString(R.string.simulation_test));
                startActivity(intent);
                break;
        }

    }
}
