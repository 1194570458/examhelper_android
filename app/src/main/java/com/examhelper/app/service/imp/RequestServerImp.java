package com.examhelper.app.service.imp;

import android.content.Context;
import android.content.SharedPreferences;

import com.examhelper.app.constant.SharePreferencesConstant;
import com.examhelper.app.entity.Certification;
import com.examhelper.app.entity.User;
import com.examhelper.app.network.RequestServerHttp;
import com.examhelper.app.service.IChapterService;
import com.examhelper.app.service.IRequestServer;
import com.examhelper.app.service.IUserService;

/**
 * Created by Administrator on 2018/7/30.
 */

public class RequestServerImp implements IRequestServer {
    IChapterService chapterService;
    IUserService userService;
    RequestServerHttp requestServerHttp;
    SharedPreferences sharedPreferences;

    public RequestServerImp(Context context) {
        chapterService = new ChapterServiceImp(context);
        userService = new UserServiceImp(context);
        requestServerHttp = RequestServerHttp.getInstance(context);
        sharedPreferences = context.getSharedPreferences(SharePreferencesConstant.APP_INIT_SP_NAME, Context.MODE_PRIVATE);
    }

    //根据本地以缓存数据重新加载网络最新数据
    public void loadData() {
        //如果是第一进入应用，那么就一次性拉取所有服务端数据
        if (!sharedPreferences.getBoolean(SharePreferencesConstant.SERVER_IS_INIT_SERVER_KEY, false)) {
            requestServerHttp.requestCertificates();
            requestServerHttp.requestPropertys();
            requestServerHttp.requestSynthesizes();
        }
        // 否则拉取服务器最新数据
        else {
            User user = userService.queryUser();
            if (user != null && user.getCertification() != null) {
                Certification certification = user.getCertification();
                int cerId = certification.getCerId();//获取已登陆用户的证书ID
                int proId = chapterService.queryLastId();//获取最大章节ID
                requestServerHttp.requestNewQuestions(proId, cerId);
            }
        }
    }
}
