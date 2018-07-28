package com.examhelper.app.entity;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * 试题Bean
 * Created by Administrator on 2018/7/24.
 */
@DatabaseTable()
public class Question implements Serializable {
    //试题ID
    @DatabaseField(id = true)
    private Integer questsionId;

    //题目内容
    @SerializedName("title")
    @DatabaseField(canBeNull = false)
    private String title;

    //选项内容
    @SerializedName("select")
    @DatabaseField(canBeNull = false)
    private String select;

    //结果选项
    @SerializedName("result")
    @DatabaseField(canBeNull = false)
    private String result;

    @DatabaseField()
    private String analysis;

    //题目所属章节,外键
    @DatabaseField(foreign = true, canBeNull = false, foreignAutoRefresh = true)
    private Chapter chapter;

    //题目所属证数ID
    @SerializedName("cerId")
    @DatabaseField()
    private Integer cerId;

    //是否为做过题
    @DatabaseField(dataType = DataType.BOOLEAN)
    private boolean isDo;

    //是否为收藏题
    @DatabaseField(dataType = DataType.BOOLEAN)
    private boolean isCollect;

    public Question() {
    }

    public Question(Integer questsionId, String title, String select, String result, String analysis, Chapter chapter, Integer cerId) {
        this.questsionId = questsionId;
        this.title = title;
        this.select = select;
        this.result = result;
        this.analysis = analysis;
        this.chapter = chapter;
        this.cerId = cerId;
    }

    public Integer getQuestsionId() {
        return questsionId;
    }

    public void setQuestsionId(Integer questsionId) {
        this.questsionId = questsionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
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

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
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
                ", title='" + title + '\'' +
                ", select=" + select +
                ", result=" + result +
                ", chapter=" + chapter +
                ", cerId=" + cerId +
                ", isDo=" + isDo +
                ", isCollect=" + isCollect +
                '}';
    }
}
