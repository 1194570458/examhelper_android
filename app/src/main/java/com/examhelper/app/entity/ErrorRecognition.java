package com.examhelper.app.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 错题Bean
 * Created by Administrator on 2018/7/24.
 */
@DatabaseTable
public class ErrorRecognition {
    //错题ID
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

    //选择错误选项
    @DatabaseField(canBeNull = false)
    private String wrongResult;

    //解析
    @DatabaseField(canBeNull = false)
    private String analysis;

    //题目所属章节,外键
    @DatabaseField(foreign = true, canBeNull = false, foreignAutoRefresh = true)
    private Chapter chapter;

    //题目所属证数ID
    @DatabaseField()
    private Integer cerId;


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

    public String getWrongResult() {
        return wrongResult;
    }

    public void setWrongResult(String wrongResult) {
        this.wrongResult = wrongResult;
    }

    public void copy(Question question) {
        setQuestsionId(question.getQuestsionId());
        setTitle(question.getTitle());
        setCerId(question.getCerId());
        setChapter(question.getChapter());
        setResult(question.getResult());
        setSelect(question.getSelect());
        setAnalysis(question.getAnalysis());
    }

    @Override
    public String toString() {
        return "ErrorRecognition{" +
                "questsionId=" + questsionId +
                ", title='" + title + '\'' +
                ", select='" + select + '\'' +
                ", result='" + result + '\'' +
                ", wrongResult='" + wrongResult + '\'' +
                ", analysis='" + analysis + '\'' +
                ", chapter=" + chapter +
                ", cerId=" + cerId +
                '}';
    }
}
