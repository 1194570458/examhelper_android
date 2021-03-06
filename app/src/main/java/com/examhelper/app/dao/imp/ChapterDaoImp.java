package com.examhelper.app.dao.imp;

import android.content.Context;

import com.examhelper.app.dao.IChapterDao;
import com.examhelper.app.db.QuestionsDbHelper;
import com.examhelper.app.entity.Chapter;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2018/7/24.
 */

public class ChapterDaoImp implements IChapterDao {
    QuestionsDbHelper questionsDbHelper;
    Dao<Chapter, Integer> characterDao;

    public ChapterDaoImp(Context context) {
        try {
            questionsDbHelper = QuestionsDbHelper.getInstance(context);
            characterDao = questionsDbHelper.getDao(Chapter.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(Chapter chapter) {
        try {
            characterDao.createOrUpdate(chapter);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void deleteAll() {
        characterDao.deleteBuilder().reset();
    }

    @Override
    public void update(Chapter chapter) {
        try {
            characterDao.update(chapter);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Chapter selectChapter(int chapterId) {
        Chapter chapter = null;
        try {
            chapter = characterDao.queryForId(chapterId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chapter;
    }

    @Override
    public List<Chapter> selectAllChapter() {
        List<Chapter> chapters = null;
        try {
            chapters = characterDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chapters;
    }

    @Override
    public int queryLastId() {
        int proId = 0;
        try {
            proId = characterDao.queryBuilder().orderBy("chapterId", false).queryForFirst().getChapterId();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return proId;
    }
}
