package com.examhelper.app.dao.imp;

import android.content.Context;

import com.examhelper.app.dao.ICertificatesDao;
import com.examhelper.app.db.QuestionsDbHelper;
import com.examhelper.app.entity.Certification;
import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

/**
 * Created by Administrator on 2018/7/29.
 */

public class CertificatesDaoImp implements ICertificatesDao {
    QuestionsDbHelper questionsDbHelper;
    Dao<Certification, Integer> certificatesDao;

    public CertificatesDaoImp(Context context) {
        try {
            questionsDbHelper = QuestionsDbHelper.getInstance(context);
            certificatesDao = questionsDbHelper.getDao(Certification.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(Certification certification) {
        try {
            //清空Certificates表，只保留一个证书数据
            certificatesDao.deleteBuilder().reset();
            certificatesDao.createOrUpdate(certification);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Certification certification) {
        try {
            certificatesDao.delete(certification);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Certification certification) {
        try {
            certificatesDao.update(certification);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Certification select() {
        CloseableIterator<Certification> iterator = certificatesDao.iterator();
        Certification certification = null;
        try {
            certification = iterator.first();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return certification;
    }
}
