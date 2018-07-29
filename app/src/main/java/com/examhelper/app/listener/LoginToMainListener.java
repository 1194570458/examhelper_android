package com.examhelper.app.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.examhelper.app.ui.activity.MainActivity;


public class LoginToMainListener implements View.OnClickListener {
    private Context context;

    public LoginToMainListener(Context context) {
        this.context = context;
    }


    @Override
    public void onClick(View v) {
//        Intent intent = new Intent(context, AnalogyExaminationActivity.class);
//        IQuestionService iQuestionService = new QuesionServiceImp(v.getContext());
//        List<Question> questions = iQuestionService.queryAllQuestions();
//        intent.putExtra(IntentFlagConstant.GET_QUESTIONS, (Serializable) questions);
//        intent.putExtra(IntentFlagConstant.IS_EXMA, true);
//        context.startActivity(intent);
        Intent intent=new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }
}
