package com.examhelper.app.service.imp;

import android.content.Context;

import com.examhelper.app.dao.IUserDao;
import com.examhelper.app.dao.imp.UserDaoImp;
import com.examhelper.app.entity.User;
import com.examhelper.app.service.IUserService;

/**
 * Created by Administrator on 2018/7/29.
 */

public class UserServiceImp implements IUserService {
    IUserDao userDao;

    public UserServiceImp(Context context) {
        userDao = new UserDaoImp(context);
    }

    @Override
    public void addUser(User user) {
        userDao.insert(user);
    }

    @Override
    public void removeUser(User user) {
        userDao.delete(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.update(user);
    }

    @Override
    public User queryUser() {
        return userDao.select();
    }
}
