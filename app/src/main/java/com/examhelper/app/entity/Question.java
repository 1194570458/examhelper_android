package com.examhelper.app.entity;

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
    @DatabaseField(canBeNull = false)
    private String title;

    //选项内容
    @DatabaseField(canBeNull = false)
    private String select;

    //结果选项
    @DatabaseField(canBeNull = false)
    private String result;

    @DatabaseField()
    private String analysis;

    //题目所属章节,外键
    @DatabaseField(foreign = true, canBeNull = false, foreignAutoRefresh = true)
    private Chapter chapter;

    //题目所属证书
    @DatabaseField(foreign = true, canBeNull = false, foreignAutoRefresh = true)
    private Certification certification;

    //是否为做过题
    @DatabaseField(dataType = DataType.BOOLEAN)
    private boolean isDo;

    //是否为收藏题
    @DatabaseField(dataType = DataType.BOOLEAN)
    private boolean isCollect;

    //选择是否正确
    private boolean isRight;

    public Question() {
    }

    public Question(Integer questsionId, String title, String select, String result, String analysis, Chapter chapter, Certification certification) {
        this.questsionId = questsionId;
        this.title = title;
        this.select = select;
        this.result = result;
        this.analysis = analysis;
        this.chapter = chapter;
        this.certification = certification;
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

    public Certification getCertification() {
        return certification;
    }

    public void setCertification(Certification certification) {
        this.certification = certification;
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

    public boolean isRight() {
        return isRight;
    }

    public void setRight(boolean right) {
        isRight = right;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questsionId=" + questsionId +
                ", title='" + title + '\'' +
                ", select='" + select + '\'' +
                ", result='" + result + '\'' +
                ", analysis='" + analysis + '\'' +
                ", chapter=" + chapter +
                ", certificates=" + certification +
                ", isDo=" + isDo +
                ", isCollect=" + isCollect +
                ", isRight=" + isRight +
                '}';
    }
}
