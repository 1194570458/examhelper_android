package com.examhelper.app.service.imp;

import android.content.Context;
import android.content.SharedPreferences;

import com.examhelper.app.constant.SharePreferencesConstant;
import com.examhelper.app.dao.IUserDao;
import com.examhelper.app.dao.imp.UserDaoImp;
import com.examhelper.app.entity.User;
import com.examhelper.app.network.RequestServerHttp;
import com.examhelper.app.service.IChapterService;
import com.examhelper.app.service.IQuestionService;
import com.examhelper.app.service.IUserService;

/**
 * Created by Administrator on 2018/7/29.
 */

public class UserServiceImp implements IUserService {
    RequestServerHttp requestServerHttp;
    IUserDao userDao;
    IQuestionService questionService;
    IChapterService chapterService;
    SharedPreferences sharedPreferences;

    public UserServiceImp(Context context) {
        userDao = new UserDaoImp(context);
        questionService = new QuesionServiceImp(context);
        chapterService = new ChapterServiceImp(context);
        requestServerHttp = RequestServerHttp.getInstance(context);
        sharedPreferences=context.getSharedPreferences(SharePreferencesConstant.APP_INIT_SP_NAME,Context.MODE_PRIVATE);
    }

    @Override
    public void login(String userName, String password) {
        requestServerHttp.requestLogin(userName, password);
    }

    @Override
    public void logout(User user) {
        userDao.delete(user);
        chapterService.removeAll();
        questionService.removeAllQuestion();
        sharedPreferences.edit().putBoolean(SharePreferencesConstant.SERVER_IS_INIT_SERVER_KEY,false).commit();
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
