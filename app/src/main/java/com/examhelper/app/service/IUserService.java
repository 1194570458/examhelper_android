package com.examhelper.app.service;

import com.examhelper.app.entity.User;

/**
 * Created by Administrator on 2018/7/29.
 */

public interface IUserService {
    /**
     * 账号密码登陆
     * @param userName
     * @param password
     */
    void login(String userName,String password);

    /**
     * 注销,删除所有数据库数据
     * @param user
     */
    void logout(User user);

    /**
     * 更新用户数据
     * @param user
     */
    void updateUser(User user);

    /**
     * 查询当前用户
     * @return
     */
    User queryUser();
}
