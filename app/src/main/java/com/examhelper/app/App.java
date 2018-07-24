package com.examhelper.app;

import android.app.Application;

import com.examhelper.app.db.QuestionsDbHelper;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化数据库
        QuestionsDbHelper.getInstance(getApplicationContext());
    }
}
