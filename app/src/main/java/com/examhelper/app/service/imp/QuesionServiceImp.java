package com.examhelper.app.service.imp;

import android.content.Context;

import com.examhelper.app.dao.IQuestionDao;
import com.examhelper.app.dao.imp.QuestionDaoImp;
import com.examhelper.app.entity.Question;
import com.examhelper.app.service.IQuestionService;

import java.util.List;

/**
 * Created by Administrator on 2018/7/24.
 */

public class QuesionServiceImp implements IQuestionService {
    IQuestionDao questionDaoImp;

    public QuesionServiceImp(Context context) {
        questionDaoImp = new QuestionDaoImp(context);
    }

    @Override
    public void addQuestion(Question question) {
        questionDaoImp.insert(question);
    }

    @Override
    public void addQuestions(List<Question> questionList) {
        questionDaoImp.insert(questionList);
    }

    @Override
    public void removeQuestion(Question question) {
        questionDaoImp.delete(question);
    }

    @Override
    public void updateQuestion(Question question) {
        questionDaoImp.update(question);
    }

    @Override
    public Question queryQuestion(int questionId) {
        Question question = questionDaoImp.selectQuestion(questionId);
        return question;
    }

    @Override
    public List<Question> queryAllQuestions() {
        List<Question> questions = questionDaoImp.selectAllQuestions();
        return questions;
    }

    @Override
    public List<Question> queryCollectedQuestion() {
        List<Question> questions = questionDaoImp.selectCollectedQuestion();
        return questions;
    }
}
