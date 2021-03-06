package com.examhelper.app.service.imp;

import android.content.Context;

import com.examhelper.app.dao.IQuestionDao;
import com.examhelper.app.dao.imp.QuestionDaoImp;
import com.examhelper.app.entity.Question;
import com.examhelper.app.service.IQuestionService;

import java.util.Collections;
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
        for (Question question : questionList) {
            addQuestion(question);
        }
    }

    @Override
    public void removeAllQuestion() {
        questionDaoImp.deleteAll();
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
    public List<Question> queryRandomQuestions() {
        List<Question> questions = questionDaoImp.selectAllQuestions();
        Collections.shuffle(questions);
        return questions;
    }

    @Override
    public List<Question> queryCollectedQuestion() {
        List<Question> questions = questionDaoImp.selectCollectedQuestion();
        return questions;
    }

    @Override
    public  List<Question> queryErrorQuestion() {
        return questionDaoImp.selectErrorQuesttions();
    }
}
