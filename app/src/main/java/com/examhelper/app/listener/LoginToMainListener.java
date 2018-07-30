package com.examhelper.app.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.examhelper.app.constant.IntentFlagConstant;
import com.examhelper.app.entity.Question;
import com.examhelper.app.service.IQuestionService;
import com.examhelper.app.service.imp.QuesionServiceImp;
import com.examhelper.app.ui.activity.AnalogyExaminationActivity;

import java.io.Serializable;
import java.util.List;


public class LoginToMainListener implements View.OnClickListener {
    private Context context;

    public LoginToMainListener(Context context) {
        this.context = context;
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, AnalogyExaminationActivity.class);
        IQuestionService iQuestionService = new QuesionServiceImp(v.getContext());
        List<Question> questions = iQuestionService.queryAllQuestions();
        intent.putExtra(IntentFlagConstant.GET_QUESTIONS, (Serializable) questions);
        intent.putExtra(IntentFlagConstant.IS_EXMA, true);
        intent.putExtra(IntentFlagConstant.PATTERN_TITLE, v.getContext().getResources().getString(R.string.simulation_test));
        context.startActivity(intent);
//        Intent intent=new Intent(context, MainActivity.class);
//        context.startActivity(intent);
    }
}
