package com.examhelper.app.dao.imp;

import android.content.Context;

import com.examhelper.app.dao.IErrorRecognitionDao;
import com.examhelper.app.db.QuestionsDbHelper;
import com.examhelper.app.entity.ErrorRecognition;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2018/7/24.
 */

public class ErrorRecognitionDaoImp implements IErrorRecognitionDao {
    QuestionsDbHelper questionsDbHelper;
    Dao<ErrorRecognition, Integer> errorRecognitionDao;

    public ErrorRecognitionDaoImp(Context context) {
        try {
            questionsDbHelper = QuestionsDbHelper.getInstance(context);
            errorRecognitionDao = questionsDbHelper.getDao(ErrorRecognition.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(ErrorRecognition errorRecognition) {
        try {
            errorRecognitionDao.create(errorRecognition);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void delete(ErrorRecognition errorRecognition) {
        try {
            errorRecognitionDao.deleteById(errorRecognition.getQuestsionId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(ErrorRecognition errorRecognition) {
        try {
            errorRecognitionDao.update(errorRecognition);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ErrorRecognition selectErrorRecognition(int questionId) {
        ErrorRecognition queryQuestion = null;
        try {
            queryQuestion = errorRecognitionDao.queryForId(questionId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return queryQuestion;
    }

    @Override
    public List<ErrorRecognition> selectErrorRecognitions() {
        List<ErrorRecognition> questions = null;
        try {
            questions = errorRecognitionDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }
}
