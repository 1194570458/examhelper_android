package com.examhelper.app.service.imp;

import android.content.Context;

import com.examhelper.app.dao.IChapterDao;
import com.examhelper.app.dao.imp.ChapterDaoImp;
import com.examhelper.app.entity.Chapter;
import com.examhelper.app.service.IChapterService;

import java.util.List;

/**
 * Created by Administrator on 2018/7/24.
 */

public class ChapterServiceImp implements IChapterService {
    IChapterDao chapterDaoImp;

    public ChapterServiceImp(Context context) {
        chapterDaoImp = new ChapterDaoImp(context);
    }

    @Override
    public void addChapter(Chapter chapter) {
        chapterDaoImp.insert(chapter);
    }

    @Override
    public void addChapters(List<Chapter> chapters) {
        for (Chapter chapter : chapters) {
            chapterDaoImp.insert(chapter);
        }
    }

    @Override
    public void removeAll() {
        chapterDaoImp.deleteAll();
    }

    @Override
    public void updateChapter(Chapter chapter) {
        chapterDaoImp.update(chapter);
    }

    @Override
    public Chapter queryChapter(int chapterId) {
        Chapter chapter = chapterDaoImp.selectChapter(chapterId);
        return chapter;
    }

    @Override
    public List<Chapter> querychapters() {
        return chapterDaoImp.selectAllChapter();
    }

    @Override
    public int queryLastId() {
        return chapterDaoImp.queryLastId();
    }
}
