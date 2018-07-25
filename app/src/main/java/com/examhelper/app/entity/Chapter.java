package com.examhelper.app.entity;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 章节Bean
 * Created by Administrator on 2018/7/24.
 */
@DatabaseTable
public class Chapter {
    //试题ID
    @DatabaseField(id = true)
    @SerializedName("proId")
    private Integer chapterId;

    //题目内容
    @SerializedName("chapter")
    @DatabaseField()
    private String chapterName;

    public Chapter() {
    }

    public Chapter(Integer chapterId, String chapterName) {
        this.chapterId = chapterId;
        this.chapterName = chapterName;
    }

    //章节所有题目
    @ForeignCollectionField(eager = true)
    private ForeignCollection<Question> questions;

    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public ForeignCollection<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ForeignCollection<Question> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "chapterId=" + chapterId +
                ", chapterName='" + chapterName + '\'' +
                ", questions=" + questions +
                '}';
    }
}
