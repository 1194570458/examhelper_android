package com.examhelper.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.examhelper.app.R;
import com.examhelper.app.constant.IntentFlagConstant;
import com.examhelper.app.constant.NormalConstant;
import com.examhelper.app.entity.Question;
import com.examhelper.app.service.IQuestionService;
import com.examhelper.app.service.imp.QuesionServiceImp;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout ll_RondomPratice;
    private LinearLayout ll_WrongBook;
    private LinearLayout ll_SequencePractice;
    private LinearLayout ll_SpecialExamination;
    private LinearLayout ll_MyCollection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

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

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        IQuestionService iQuestionService = new QuesionServiceImp(v.getContext());
        switch (v.getId()) {
            //顺序练习
            case R.id.ll_SequencePractice:
                intent = new Intent(this, AnalogyExaminationActivity.class);
                iQuestionService = new QuesionServiceImp(v.getContext());
                List<Question> allQuestions = iQuestionService.queryAllQuestions();
                intent.putExtra(IntentFlagConstant.GET_QUESTIONS, (Serializable) allQuestions);
                intent.putExtra(IntentFlagConstant.PATTERN_TITLE, v.getContext().getResources().getString(R.string.sequence_practice));
                startActivity(intent);
                break;
            //错题本
            case R.id.ll_WrongBook:
                Intent intent1 = new Intent(this, WrongAndCollectionsActivity.class);
                intent1.putExtra(IntentFlagConstant.IS_WRONGORCOLLECT, NormalConstant.WRONG_BOOK);
                startActivity(intent1);
                break;
            //我的收藏
            case R.id.ll_MyCollection:
                Intent intent2 = new Intent(this, WrongAndCollectionsActivity.class);
                intent2.putExtra(IntentFlagConstant.IS_WRONGORCOLLECT, NormalConstant.MY_COLLECTION);
                startActivity(intent2);
                break;

            //随机练习
            case R.id.ll_RondomPratice:
                intent = new Intent(this, AnalogyExaminationActivity.class);
                List<Question> allRandomQuestion = iQuestionService.queryRandomQuestions();
                intent.putExtra(IntentFlagConstant.GET_QUESTIONS, (Serializable) allRandomQuestion);
                intent.putExtra(IntentFlagConstant.PATTERN_TITLE, v.getContext().getResources().getString(R.string.rondom_pratice));
                startActivity(intent);
                break;
            //专项考试
            case R.id.ll_SpecialExamination:
                Toast.makeText(this, "暂无此功能", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
