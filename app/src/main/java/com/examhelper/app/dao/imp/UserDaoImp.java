package com.examhelper.app.dao.imp;

import android.content.Context;

import com.examhelper.app.dao.IUserDao;
import com.examhelper.app.db.QuestionsDbHelper;
import com.examhelper.app.entity.User;
import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

/**
 * Created by Administrator on 2018/7/29.
 */

public class UserDaoImp implements IUserDao {
    QuestionsDbHelper questionsDbHelper;
    Dao<User, Integer> userDao;

    public UserDaoImp(Context context) {
        try {
            questionsDbHelper = QuestionsDbHelper.getInstance(context);
            userDao = questionsDbHelper.getDao(User.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(User user) {
        try {
            //清空user表，只保留一个用户
            userDao.deleteBuilder().reset();
            userDao.createOrUpdate(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User user) {
        try {
            userDao.delete(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        try {
            userDao.update(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User select() {
        CloseableIterator<User> iterator = userDao.iterator();
        User user = null;
        try {
            user = iterator.first();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
