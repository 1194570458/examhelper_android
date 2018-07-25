package com.examhelper.app.service;

import com.examhelper.app.entity.ErrorRecognition;

import java.util.List;

/**
 * Created by Administrator on 2018/7/24.
 */

public interface IErrorRecognitionService {

    void addQuestion(ErrorRecognition errorRecognition);

    void removeQuestion(ErrorRecognition errorRecognition);

    List<ErrorRecognition> queryQuestions();
}
