package com.examhelper.app.network;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.examhelper.app.constant.HttpConstant;
import com.examhelper.app.constant.SharePreferencesConstant;
import com.examhelper.app.dao.IUserDao;
import com.examhelper.app.dao.imp.UserDaoImp;
import com.examhelper.app.entity.Certification;
import com.examhelper.app.entity.Chapter;
import com.examhelper.app.entity.Question;
import com.examhelper.app.entity.User;
import com.examhelper.app.messageevent.LoginEvent;
import com.examhelper.app.messageevent.RegisterEvent;
import com.examhelper.app.service.ICertificatesService;
import com.examhelper.app.service.IChapterService;
import com.examhelper.app.service.IQuestionService;
import com.examhelper.app.service.imp.CertificatesServiceImp;
import com.examhelper.app.service.imp.ChapterServiceImp;
import com.examhelper.app.service.imp.QuesionServiceImp;
import com.examhelper.app.ui.activity.RegisterAcitivity;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取试题
 * Created by Administrator on 2018/7/24.
 */
public class RequestServerHttp {
    private static Context mContext;
    private RequestQueue requestQueue;
    private static RequestServerHttp requestServerHttp;
    private IChapterService chapterService;
    private IQuestionService questionService;
    private ICertificatesService certificatesService;
    private IUserDao userDao;
    private SharedPreferences sharedPreferences;

    private RequestServerHttp(Context context) {
        sharedPreferences = context.getSharedPreferences(SharePreferencesConstant.APP_INIT_SP_NAME, Context.MODE_PRIVATE);
        chapterService = new ChapterServiceImp(context);
        questionService = new QuesionServiceImp(context);
        certificatesService = new CertificatesServiceImp(context);
        userDao = new UserDaoImp(context);
        requestQueue = Volley.newRequestQueue(context);
    }


    public static RequestServerHttp getInstance(Context context) {
        mContext = context;
        if (requestServerHttp == null) {
            synchronized (RequestServerHttp.class) {
                if (requestServerHttp == null) {
                    requestServerHttp = new RequestServerHttp(context);
                }
            }
        }
        return requestServerHttp;
    }


    /**
     * 获取所有章节
     */
    public void requestPropertys() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(HttpConstant.API_ALL_PROPERTYS, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("GetQuestionHttp", "API:" + HttpConstant.API_ALL_PROPERTYS + ":response:" + response);
                try {
                    if (response.getInt("code") == 0) {
                        sharedPreferences.edit().putBoolean(SharePreferencesConstant.SERVER_IS_INIT_SERVER_KEY, true).commit();
                        List<Chapter> chapters = new ArrayList<>();
                        JSONArray data = response.getJSONArray("data");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject jsonObject = data.getJSONObject(i);
                            Chapter chapter = new Chapter(jsonObject.getInt("proId"),
                                    jsonObject.getString("chapter"));
                            chapters.add(chapter);
                        }
                        Log.d("GetQuestionHttp", "chapters:" + chapters);
                        //添加到数据库
                        chapterService.addChapters(chapters);
                        //获取试题数据
                        requestSynthesizes();
                    } else {
                        sharedPreferences.edit().putBoolean(SharePreferencesConstant.SERVER_IS_INIT_SERVER_KEY, false).commit();
                    }
                } catch (JSONException e) {
                    sharedPreferences.edit().putBoolean(SharePreferencesConstant.SERVER_IS_INIT_SERVER_KEY, false).commit();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                sharedPreferences.edit().putBoolean(SharePreferencesConstant.SERVER_IS_INIT_SERVER_KEY, false).commit();
                if (error != null) {
                    Log.e("GetQuestionHttp", error.toString());
                }
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    /**
     * 根据本地最新章节ID获取服务器最新章节，然后通过章节ID和证书ID获取最新试题
     *
     * @param proId
     * @param cerId
     */
    public void requestNewQuestions(int proId, final int cerId) {
        final String url = String.format(HttpConstant.API_NEW_PROPERTYS, String.valueOf(proId));
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("GetQuestionHttp", "API:" + url + ":response:" + response);
                        try {
                            if (response.getInt("code") == 0) {
                                List<Chapter> chapters = new ArrayList<>();
                                JSONArray data = response.getJSONArray("data");
                                for (int i = 0; i < data.length(); i++) {
                                    JSONObject jsonObject = data.getJSONObject(i);
                                    Chapter chapter = new Chapter(jsonObject.getInt("proId"), jsonObject.getString("chapter"));
                                    Log.d("GetQuestionHttp", "chapter:" + chapter);
                                    chapters.add(chapter);
                                    //获取该章节试题
                                    requestSynthesizesById(chapter.getChapterId(), cerId);
                                }
                                //添加到数据库
                                chapterService.addChapters(chapters);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error != null) {
                            Log.e("GetQuestionHttp", error.toString());
                        }
                    }
                });
        requestQueue.add(jsonObjectRequest);
    }


    /**
     * 获取所有试题
     */
    public void requestSynthesizes() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(HttpConstant.API_ALL_SYNTHESIZES, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("GetQuestionHttp", "API:" + HttpConstant.API_ALL_SYNTHESIZES + ":response:" + response);
                try {
                    if (response.getInt("code") == 0) {
                        sharedPreferences.edit().putBoolean(SharePreferencesConstant.SERVER_IS_INIT_SERVER_KEY, true).commit();
                        JSONArray data = response.getJSONArray("data");
                        List<Question> questions = new ArrayList<>();
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject jsonObject = data.getJSONObject(i);
                            Chapter chapter = new Chapter();
                            chapter.setChapterId(jsonObject.getInt("proId"));
                            Certification certification = new Certification();
                            certification.setCerId(jsonObject.getInt("cerId"));
                            Question question = new Question(
                                    jsonObject.getInt("synId"),
                                    jsonObject.getString("title"),
                                    jsonObject.getString("select"),
                                    jsonObject.getString("result"),
                                    jsonObject.getString("analysis"),
                                    chapter,
                                    certification
                            );
                            Log.d("GetQuestionHttp", "chapter:" + question);
                            questions.add(question);
                        }
                        questionService.addQuestions(questions);
                    } else {
                        sharedPreferences.edit().putBoolean(SharePreferencesConstant.SERVER_IS_INIT_SERVER_KEY, false).commit();
                    }
                } catch (JSONException e) {
                    sharedPreferences.edit().putBoolean(SharePreferencesConstant.SERVER_IS_INIT_SERVER_KEY, false).commit();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                sharedPreferences.edit().putBoolean(SharePreferencesConstant.SERVER_IS_INIT_SERVER_KEY, false).commit();
                if (error != null) {
                    Log.e("GetQuestionHttp", error.toString());
                }
            }
        });
        requestQueue.add(jsonObjectRequest);
    }


    /**
     * 根据章节ID和证数ID获取试题
     *
     * @param proId
     * @param cerId
     */
    private void requestSynthesizesById(int proId, int cerId) {
        final String url = String.format(HttpConstant.API_SYNTHESIZES_BY_ID, String.valueOf(proId), String.valueOf(cerId));
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("GetQuestionHttp", "API:" + url + ":response:" + response);
                try {
                    if (response.getInt("code") == 0) {
                        JSONArray data = response.getJSONArray("data");
                        List<Question> questions = new ArrayList<>();
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject jsonObject = data.getJSONObject(i);
                            Chapter chapter = new Chapter();
                            chapter.setChapterId(jsonObject.getInt("proId"));
                            Certification certification = new Certification();
                            certification.setCerId(jsonObject.getInt("cerId"));
                            Question question = new Question(
                                    jsonObject.getInt("synId"),
                                    jsonObject.getString("title"),
                                    jsonObject.getString("select"),
                                    jsonObject.getString("result"),
                                    jsonObject.getString("analysis"),
                                    chapter,
                                    certification
                            );
                            Log.d("GetQuestionHttp", "chapter:" + question);
                            questions.add(question);
                        }
                        questionService.addQuestions(questions);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error != null) {
                    Log.e("GetQuestionHttp", error.toString());
                }
            }
        });
        requestQueue.add(jsonObjectRequest);
    }


    /**
     * 获取所有证书数据
     */
    public void requestCertificates() {
        final String url = HttpConstant.API_CERTIFICATIONS;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("GetQuestionHttp", "API:" + url + ":response:" + response);
                try {
                    if (response.getInt("code") == 0) {
                        sharedPreferences.edit().putBoolean(SharePreferencesConstant.SERVER_IS_INIT_SERVER_KEY, true).commit();
                        JSONArray data = response.getJSONArray("data");
                        List<Certification> certifications = new ArrayList<>();
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject jsonObject = data.getJSONObject(i);
                            Certification certification = new Gson().fromJson(jsonObject.toString(), Certification.class);
                            certifications.add(certification);
                            Log.d("RequestServerHttp", "certification:" + certification);
                        }
                        certificatesService.addCertificates(certifications);
                    } else {
                        sharedPreferences.edit().putBoolean(SharePreferencesConstant.SERVER_IS_INIT_SERVER_KEY, false).commit();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    sharedPreferences.edit().putBoolean(SharePreferencesConstant.SERVER_IS_INIT_SERVER_KEY, false).commit();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                sharedPreferences.edit().putBoolean(SharePreferencesConstant.SERVER_IS_INIT_SERVER_KEY, false).commit();
                if (error != null) {
                    Log.e("GetQuestionHttp", error.toString());
                }
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    /**
     * 用户注册
     *
     * @param params
     */
    public void requestRegister(String params) {
        final String url = HttpConstant.API_REGISTER;
        JsonObjectRequest jsonObjectRequest = null;
        try {
            jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.POST, url,
                    new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("GetQuestionHttp", "API:" + url + ":response:" + response);
                            try {
                                if (response.getInt("code") == 0) {
                                    JSONObject jsonObject = response.getJSONObject("data");
                                    Certification certification = new Certification();
                                    certification.setCerId(jsonObject.getInt("cerId"));
                                    User user = new User(
                                            jsonObject.getInt("userId"),
                                            jsonObject.getString("username"),
                                            jsonObject.getString("password"),
                                            jsonObject.getInt("role"),
                                            jsonObject.getString("createDate"),
                                            jsonObject.getInt("status"),
                                            jsonObject.getString("wecharOpenid"),
                                            jsonObject.getString("qqNumber"),
                                            jsonObject.getString("qqOpenid"),
                                            certification
                                    );
                                    //保存用户信息
                                    userDao.insert(user);
                                    //通知注册成功
                                    EventBus.getDefault().post(new RegisterEvent(RegisterEvent.TYPE_SUCCESS, user));
                                    //关闭注册界面
                                    ((RegisterAcitivity) mContext).finish();
                                } else {
                                    //通知注册失败
                                    EventBus.getDefault().post(new RegisterEvent(RegisterEvent.TYPE_FAILURE));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                //通知注册失败
                                EventBus.getDefault().post(new RegisterEvent(RegisterEvent.TYPE_FAILURE));
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            if (error != null) {
                                Log.e("GetQuestionHttp", error.toString());
                                //通知注册失败
                                EventBus.getDefault().post(new RegisterEvent(RegisterEvent.TYPE_FAILURE));
                            }
                        }
                    });
        } catch (JSONException e) {
            e.printStackTrace();
        }
        requestQueue.add(jsonObjectRequest);
    }


    /**
     * 登陆请求
     *
     * @param userName
     * @param password
     */
    public void requestLogin(String userName, String password) {
        final String url = String.format(HttpConstant.API_LOGIN, userName, password);
        final int[] status = new int[1];
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("GetQuestionHttp", "API:" + url + ":response:" + response);
                try {
                    if (response.getInt("code") == 0) {
                        JSONObject jsonObject = response.getJSONObject("data");
                        Certification certification = new Certification();
                        certification.setCerId(jsonObject.getInt("cerId"));
                        User user = new User(
                                jsonObject.getInt("userId"),
                                jsonObject.getString("username"),
                                jsonObject.getString("password"),
                                jsonObject.getInt("role"),
                                jsonObject.getString("createDate"),
                                jsonObject.getInt("status"),
                                jsonObject.getString("wecharOpenid"),
                                jsonObject.getString("qqNumber"),
                                jsonObject.getString("qqOpenid"),
                                certification
                        );
                        //保存用户信息
                        userDao.insert(user);
                        //通知登陆成功
                        EventBus.getDefault().post(new LoginEvent(LoginEvent.TYPE_SUCCESS, user));
                    } else {
                        //通知登陆失败
                        EventBus.getDefault().post(new LoginEvent(LoginEvent.TYPE_FAILURE));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    //通知登陆失败
                    EventBus.getDefault().post(new LoginEvent(LoginEvent.TYPE_FAILURE));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error != null) {
                    Log.e("GetQuestionHttp", error.toString());
                    //通知登陆失败
                    EventBus.getDefault().post(new LoginEvent(LoginEvent.TYPE_FAILURE));
                }
            }
        });
        requestQueue.add(jsonObjectRequest);
    }


}
