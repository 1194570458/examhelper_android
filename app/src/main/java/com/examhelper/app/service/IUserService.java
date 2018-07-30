package com.examhelper.app.service;

import com.examhelper.app.entity.User;

/**
 * Created by Administrator on 2018/7/29.
 */

public interface IUserService {
    void addUser(User user);

    void removeUser(User user);

    void updateUser(User user);

    User queryUser();
}
