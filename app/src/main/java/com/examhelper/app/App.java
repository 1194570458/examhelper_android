package com.examhelper.app;

import android.app.Application;
import android.util.Log;

import com.examhelper.app.db.QuestionsDbHelper;
import com.examhelper.app.service.IQuestionService;
import com.examhelper.app.service.IRequestServer;
import com.examhelper.app.service.imp.QuesionServiceImp;
import com.examhelper.app.service.imp.RequestServerImp;

public class App extends Application {
    IRequestServer requestServer;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("App", "onCreate");
        //初始化数据库
        QuestionsDbHelper.getInstance(this);
        //从网络获取试题
        requestServer = new RequestServerImp(this);
        requestServer.loadData();
        IQuestionService questionService=new QuesionServiceImp(this);
        questionService.queryRandomQuestions();
    }
}
