package com.examhelper.app.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.examhelper.app.R;
import com.examhelper.app.constant.IntentFlagConstant;
import com.examhelper.app.entity.Question;
import com.examhelper.app.ui.activity.AnalogyExaminationActivity;

import java.io.Serializable;
import java.util.List;


public class ErrorToDetailListener implements View.OnClickListener {
    private Context context;
    private List<Question> questions;
    private int position;

    public ErrorToDetailListener(Context context, List<Question> questions, int position) {
        this.context = context;
        this.questions = questions;
        this.position = position;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, AnalogyExaminationActivity.class);
//        IQuestionService iQuestionService = new QuesionServiceImp(v.getContext());
//        List<Question> questions = iQuestionService.queryAllQuestions();
        intent.putExtra(IntentFlagConstant.GET_WRONG_QUESTIONS, (Serializable) questions);
        intent.putExtra(IntentFlagConstant.IS_EXMA, false);
        intent.putExtra(IntentFlagConstant.PATTERN_TITLE, v.getContext().getResources().getString(R.string.wrong_topic));
        intent.putExtra(IntentFlagConstant.GET_WRONG_POSITION,position);
        context.startActivity(intent);
//        Intent intent=new Intent(context, MainActivity.class);
//        context.startActivity(intent);
    }
}
