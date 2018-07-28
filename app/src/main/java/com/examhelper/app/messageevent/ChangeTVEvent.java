package com.examhelper.app.messageevent;

/**
 * Created by Administrator on 2018/7/28.
 */

public class ChangeTVEvent {
    private String contentText;
    private int color;

    public ChangeTVEvent() {
    }

    public ChangeTVEvent(String contentText, int color) {
        this.contentText = contentText;
        this.color = color;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
