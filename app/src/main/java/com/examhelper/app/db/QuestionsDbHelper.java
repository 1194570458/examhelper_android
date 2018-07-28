package com.examhelper.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.examhelper.app.constant.DbConstant;
import com.examhelper.app.entity.Chapter;
import com.examhelper.app.entity.ErrorRecognition;
import com.examhelper.app.entity.Question;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


/**
 * 试题保存数据库
 * Created by Administrator on 2018/7/24.
 */

public class QuestionsDbHelper extends OrmLiteSqliteOpenHelper {

    private static QuestionsDbHelper questionsDbHelper;

    private Map<String, Dao> daos = new HashMap<String, Dao>();

    private QuestionsDbHelper(Context context) {
        super(context, DbConstant.Question_DB_NAME, null, DbConstant.DB_VERSION);
    }

    /**
     * 获取单例方法
     *
     * @param context
     * @return
     */
    public synchronized static QuestionsDbHelper getInstance(Context context) {
        if (questionsDbHelper == null) {
            synchronized (QuestionsDbHelper.class) {
                if (questionsDbHelper == null) {
                    questionsDbHelper = new QuestionsDbHelper(context);
                }
            }
        }
        return questionsDbHelper;
    }


    /**
     * 数据库创建时创建表
     *
     * @param database
     * @param connectionSource
     */
    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            Log.e("QuestionsDbHelper", "onCreate");
            TableUtils.createTable(connectionSource, Chapter.class);
            TableUtils.createTable(connectionSource, Question.class);
            TableUtils.createTable(connectionSource, ErrorRecognition.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /***
     *  数据库更新时删除表后重新创建表
     * @param database
     * @param connectionSource
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Chapter.class, true);
            TableUtils.dropTable(connectionSource, Question.class, true);
            TableUtils.dropTable(connectionSource, ErrorRecognition.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取对应表操作的实例Dao
     *
     * @param clazz
     * @return
     * @throws SQLException
     */
    public synchronized Dao getDao(Class clazz) throws SQLException {
        Dao dao = null;
        String className = clazz.getSimpleName();
        //使用一个Map来保持所有的Dao对象，只有第一次调用时才会去调用底层的getDao()
        if (daos.containsKey(className)) {
            dao = daos.get(className);
        }
        if (dao == null) {
            dao = super.getDao(clazz);
            daos.put(className, dao);
        }
        return dao;
    }

    /**
     * 释放资源
     */
    @Override
    public void close() {
        super.close();
        for (String key : daos.keySet()) {
            Dao dao = daos.get(key);
            dao = null;
        }
    }
}
