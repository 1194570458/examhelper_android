package com.examhelper.app.dao.imp;

import android.content.Context;

import com.examhelper.app.dao.IQuestionDao;
import com.examhelper.app.db.QuestionsDbHelper;
import com.examhelper.app.entity.Question;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2018/7/24.
 */

public class QuestionDaoImp implements IQuestionDao {
    QuestionsDbHelper questionsDbHelper;
    Dao<Question, Integer> questionsDao;

    public QuestionDaoImp(Context context) {
        try {
            questionsDbHelper = QuestionsDbHelper.getInstance(context);
            questionsDao = questionsDbHelper.getDao(Question.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(Question question) {
        try {
            questionsDao.createOrUpdate(question);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void delete(Question question) {
        try {
            questionsDao.deleteById(question.getQuestsionId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Question question) {
        try {
            questionsDao.update(question);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Question selectQuestion(int questionId) {
        Question queryQuestion = null;
        try {
            queryQuestion = questionsDao.queryForId(questionId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return queryQuestion;
    }

    @Override
    public List<Question> selectAllQuestions() {
        List<Question> questions = null;
        try {
            questions = questionsDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }

    @Override
    public List<Question> selectCollectedQuestion() {
        List<Question> questionList = null;
        try {
            questionList = questionsDao.queryForEq("isCollect", true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questionList;
    }

    @Override
    public List<Question> selectErrorQuesttions() {
        List<Question> questions = null;
        try {
            questions = questionsDao.queryForEq("isWrong", true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }
}
