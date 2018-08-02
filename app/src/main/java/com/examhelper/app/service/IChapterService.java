package com.examhelper.app.service;

import com.examhelper.app.entity.Chapter;

import java.util.List;

/**
 * Created by Administrator on 2018/7/24.
 */

public interface IChapterService {

    void addChapter(Chapter chapter);

    void addChapters(List<Chapter >chapters);

    void removeChapter(Chapter chapter);

    void updateChapter(Chapter chapter);

    Chapter queryChapter(int chapterId);

    int queryLastId();
}
