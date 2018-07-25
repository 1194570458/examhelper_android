package com.examhelper.app;

import android.app.Application;
import android.util.Log;

import com.examhelper.app.db.QuestionsDbHelper;
import com.examhelper.app.network.GetQuestionHttp;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("App", "onCreate");
        //初始化数据库
        QuestionsDbHelper.getInstance(this);
        //从网络获取试题
        GetQuestionHttp.getInstance(this);
    }
}
