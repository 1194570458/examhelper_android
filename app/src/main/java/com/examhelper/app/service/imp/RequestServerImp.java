package com.examhelper.app.service.imp;

import android.content.Context;

import com.examhelper.app.entity.Certification;
import com.examhelper.app.network.RequestServerHttp;
import com.examhelper.app.service.ICertificatesService;
import com.examhelper.app.service.IChapterService;
import com.examhelper.app.service.IRequestServer;

/**
 * Created by Administrator on 2018/7/30.
 */

public class RequestServerImp implements IRequestServer {
    ICertificatesService certificatesService;
    IChapterService chapterService;
    RequestServerHttp requestServerHttp;

    public RequestServerImp(Context context) {
        certificatesService = new CertificatesServiceImp(context);
        chapterService = new ChapterServiceImp(context);
        requestServerHttp = RequestServerHttp.getInstance(context);
    }

    //根据本地以缓存数据重新加载网络最新数据
    public void loadData() {
        long latestProId = chapterService.queryCount();
        Certification certification = certificatesService.queryCertificates();
        //如果本地已有缓存数据，那么就下拉服务器最新的章节试题数据
        if (latestProId > 0 & certification != null) {
            requestServerHttp.requestNewQuestions((int) latestProId, certification.getCerId());
        }
        //如果本地没有如何数据，则直接下拉所有试题数据
        else {
            requestServerHttp.requestPropertys();
        }
    }
}
