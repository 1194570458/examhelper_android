package com.examhelper.app.messageevent;

/**
 * Created by Administrator on 2018/7/28.
 */

public class NotifyBackDialogEvent {
    public static final int IS_TIME = 0;
    public static final int IS_END = 1;
    public static final int OTHER = 2;

    private int type;

    public NotifyBackDialogEvent(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
