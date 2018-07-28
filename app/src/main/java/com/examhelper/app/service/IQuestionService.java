package com.examhelper.app.service;

import com.examhelper.app.entity.Question;

import java.util.List;

/**
 * Created by Administrator on 2018/7/24.
 */

public interface IQuestionService {

    void addQuestion(Question question);

    void addQuestions(List<Question> questionList);

    void removeQuestion(Question question);

    void updateQuestion(Question question);

    Question queryQuestion(int questionId);

    List<Question> queryAllQuestions();

    List<Question> queryCollectedQuestion();
}
