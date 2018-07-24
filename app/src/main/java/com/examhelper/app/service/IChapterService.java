package com.examhelper.app.service;

import com.examhelper.app.entity.Chapter;

/**
 * Created by Administrator on 2018/7/24.
 */

public interface IChapterService {

    void addChapter(Chapter chapter);

    void removeChapter(Chapter chapter);

    void updateChapter(Chapter chapter);

    Chapter queryChapter(int chapterId);
}
