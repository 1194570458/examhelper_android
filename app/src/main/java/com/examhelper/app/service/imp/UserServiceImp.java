package com.examhelper.app.service.imp;

import android.content.Context;

import com.examhelper.app.dao.IUserDao;
import com.examhelper.app.dao.imp.UserDaoImp;
import com.examhelper.app.entity.User;
import com.examhelper.app.network.RequestServerHttp;
import com.examhelper.app.service.IUserService;

/**
 * Created by Administrator on 2018/7/29.
 */

public class UserServiceImp implements IUserService {
    RequestServerHttp requestServerHttp;
    IUserDao userDao;

    public UserServiceImp(Context context) {
        userDao = new UserDaoImp(context);
        requestServerHttp = RequestServerHttp.getInstance(context);
    }

    @Override
    public void login(String userName, String password) {
        requestServerHttp.requestLogin(userName,password);
    }

    @Override
    public void logout(User user) {
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
