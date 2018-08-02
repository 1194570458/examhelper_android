package com.examhelper.app.messageevent;

import com.examhelper.app.entity.User;

/**
 * Created by Administrator on 2018/7/30.
 */

public class RegisterEvent {
    public static final int TYPE_SUCCESS = 0;//成功，User!null
    public static final int TYPE_FAILURE = 1;//失败  User=null

    private int type;
    private User user;

    public RegisterEvent() {
    }

    public RegisterEvent(int type, User user) {
        this.type = type;
        this.user = user;
    }

    public RegisterEvent(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "RegisterEvent{" +
                "type=" + type +
                ", user=" + user +
                '}';
    }
}
