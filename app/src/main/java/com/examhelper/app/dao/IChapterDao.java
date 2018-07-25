package com.examhelper.app.dao;

import com.examhelper.app.entity.Chapter;

/**
 * Created by Administrator on 2018/7/24.
 */

public interface IChapterDao {
    /**
     * 插入单个
     * @param chapter
     */
    void insert(Chapter chapter);


    /**
     * 删除
     * @param chapter
     */
    void delete(Chapter chapter);

    /**
     * 更新
     * @param chapter
     */
    void update(Chapter chapter);

    /**
     * 根据主键查询
     * @param chapterId
     * @return
     */
    Chapter selectChapter(int chapterId);
}
