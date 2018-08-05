package com.examhelper.app.dao;

import com.examhelper.app.entity.Chapter;

import java.util.List;

/**
 * Created by Administrator on 2018/7/24.
 */

public interface IChapterDao {
    /**
     * 更新或者插入
     *
     * @param chapter
     */
    void insert(Chapter chapter);


    /**
     * 删除所有
     */
    void deleteAll();

    /**
     * 更新
     *
     * @param chapter
     */
    void update(Chapter chapter);

    /**
     * 根据主键查询
     *
     * @param chapterId
     * @return
     */
    Chapter selectChapter(int chapterId);

    /**
     * 查询所有
     *
     * @return
     */
    List<Chapter> selectAllChapter();

    //查询最大的章节ID
    int queryLastId();
}
