package com.examhelper.app.service;

import com.examhelper.app.entity.Chapter;

import java.util.List;

/**
 * Created by Administrator on 2018/7/24.
 */

public interface IChapterService {

    /**
     * 添加一个章节数据
     * @param chapter
     */
    void addChapter(Chapter chapter);

    /**
     * 添加多个章节数据
     * @param chapters
     */
    void addChapters(List<Chapter> chapters);

    /**
     * 移除所有章节数据
     */
    void removeAll();

    /**
     * 更新一个章节数据
     * @param chapter
     */
    void updateChapter(Chapter chapter);

    /**
     * 根据章节ID查询一个章节数据
     * @param chapterId
     * @return
     */
    Chapter queryChapter(int chapterId);

    /**
     * 查询所有章节数据
     * @return
     */
    List<Chapter> querychapters();

    /**
     * 查询最大章节ID
     * @return
     */
    int queryLastId();
}
