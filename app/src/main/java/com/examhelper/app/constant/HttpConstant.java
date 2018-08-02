package com.examhelper.app.constant;

/**
 * Created by Administrator on 2018/7/24.
 */

public class HttpConstant {
//    private static final String CONNECTION_SERVICE_ADDRESS = "http://10.0.2.2:8000/";//JR服务器地址
    private static final String CONNECTION_SERVICE_ADDRESS = "http://10.32.142.189:8080/";//Jun服务器地址
    public static final String API_ALL_SYNTHESIZES = CONNECTION_SERVICE_ADDRESS + "synthesizes";//获取所有题目
    public static final String API_SYNTHESIZES_BY_ID = CONNECTION_SERVICE_ADDRESS + "synthesizes/%s";//根据章节ID和证数ID获取试题
    public static final String API_ALL_PROPERTYS = CONNECTION_SERVICE_ADDRESS + "propertys";//获取所有章节
    public static final String API_NEW_PROPERTYS = CONNECTION_SERVICE_ADDRESS + "property/new/%s";//获取最新章节
    public static final String API_LOGIN = CONNECTION_SERVICE_ADDRESS + "user?username=%s&password=%s";//登陆
    public static final String API_REGISTER = CONNECTION_SERVICE_ADDRESS + "user";//注册
    public static final String API_CERTIFICATIONS = CONNECTION_SERVICE_ADDRESS + "certificates";//获取所有证书
    public static final String API_CERTIFICATION = CONNECTION_SERVICE_ADDRESS + "certificate/%s";//根据证书ID获取证书数据

}
