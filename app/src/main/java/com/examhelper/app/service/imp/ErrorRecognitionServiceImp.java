package com.examhelper.app.service.imp;

import android.content.Context;

import com.examhelper.app.dao.IErrorRecognitionDao;
import com.examhelper.app.dao.imp.ErrorRecognitionDaoImp;
import com.examhelper.app.entity.ErrorRecognition;
import com.examhelper.app.service.IErrorRecognitionService;

import java.util.List;

/**
 * Created by Administrator on 2018/7/24.
 */

public class ErrorRecognitionServiceImp implements IErrorRecognitionService {
    IErrorRecognitionDao errorRecognitionDaoImp;

    public ErrorRecognitionServiceImp(Context context) {
        errorRecognitionDaoImp = new ErrorRecognitionDaoImp(context);
    }

    @Override
    public void addErrorRecognition(ErrorRecognition errorRecognition) {
        errorRecognitionDaoImp.insert(errorRecognition);
    }


    @Override
    public void removeErrorRecognition(ErrorRecognition errorRecognition) {
        errorRecognitionDaoImp.delete(errorRecognition);
    }


    @Override
    public List<ErrorRecognition> queryErrorRecognition() {
        List<ErrorRecognition> errorRecognitions = errorRecognitionDaoImp.selectErrorRecognitions();
        return errorRecognitions;
    }
}
