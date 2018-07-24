package com.examhelper.app.entity;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Arrays;

/**
 * 试题Bean
 * Created by Administrator on 2018/7/24.
 */
@DatabaseTable()
public class Question {
    //试题ID
    @DatabaseField(id = true, generatedId = true)
    private Integer questsionId;

    //题目内容
    @DatabaseField(canBeNull = false)
    private String topic;

    //选项内容
    @DatabaseField(canBeNull = false)
    private String[] select;

    //结果选项
    @DatabaseField(canBeNull = false)
    private Integer result;

    //题目所属章节,外键
    @DatabaseField(foreign = true, canBeNull = false, foreignAutoRefresh = true)
    private Chapter chapter;

    //题目所属证数ID
    @DatabaseField()
    private Integer cerId;

    //是否为做过题
    @DatabaseField(dataType = DataType.BOOLEAN)
    private boolean isDo;

    //是否为收藏题
    @DatabaseField(dataType = DataType.BOOLEAN)
    private boolean isCollect;


    public Integer getQuestsionId() {
        return questsionId;
    }

    public void setQuestsionId(Integer questsionId) {
        this.questsionId = questsionId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String[] getSelect() {
        return select;
    }

    public void setSelect(String[] select) {
        this.select = select;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public Integer getCerId() {
        return cerId;
    }

    public void setCerId(Integer cerId) {
        this.cerId = cerId;
    }

    public boolean isCollect() {
        return isCollect;
    }

    public void setCollect(boolean collect) {
        isCollect = collect;
    }

    public boolean isDo() {
        return isDo;
    }

    public void setDo(boolean aDo) {
        isDo = aDo;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questsionId=" + questsionId +
                ", topic='" + topic + '\'' +
                ", select=" + Arrays.toString(select) +
                ", result=" + result +
                ", chapter=" + chapter +
                ", cerId=" + cerId +
                ", isDo=" + isDo +
                ", isCollect=" + isCollect +
                '}';
    }
}
