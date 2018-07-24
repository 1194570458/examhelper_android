package com.examhelper.app.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.List;

/**章节Bean
 * Created by Administrator on 2018/7/24.
 */
@DatabaseTable
public class Chapter {
    //试题ID
    @DatabaseField(id = true, foreign = true)
    private Integer chapterId;

    //题目内容
    @DatabaseField()
    private String chapterName;

    //章节所有题目
    @ForeignCollectionField(eager = true)
    private List<Question> questions;

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

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
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
