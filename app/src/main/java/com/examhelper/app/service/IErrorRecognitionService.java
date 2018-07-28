package com.examhelper.app.service;

import com.examhelper.app.entity.ErrorRecognition;

import java.util.List;

/**
 * Created by Administrator on 2018/7/24.
 */

public interface IErrorRecognitionService {

    void addErrorRecognition(ErrorRecognition errorRecognition);

    void removeErrorRecognition(ErrorRecognition errorRecognition);

    List<ErrorRecognition> queryErrorRecognition();
}
