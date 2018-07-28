package com.examhelper.app.messageevent;

/**
 * Created by Administrator on 2018/7/28.
 */

public class IsTimeShowEvent {
    public static final int IS_TIME = 0;
    public static final int IS_END = 1;
    private int type;

    public IsTimeShowEvent() {
    }

    public IsTimeShowEvent(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
