package com.examhelper.app.service;

import com.examhelper.app.entity.User;

/**
 * Created by Administrator on 2018/7/29.
 */

public interface IUserService {
    void login(String userName,String password);

    void logout(User user);

    void updateUser(User user);

    User queryUser();
}
