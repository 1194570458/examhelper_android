package com.examhelper.app.dao;

import com.examhelper.app.entity.User;

/**
 * Created by Administrator on 2018/7/29.
 */

public interface IUserDao {
    void insert(User user);

    void delete(User user);

    void update(User user);

    User select();
}
