package com.examhelper.app.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.examhelper.app.constant.HttpConstant;
import com.examhelper.app.entity.Certification;
import com.examhelper.app.entity.Chapter;
import com.examhelper.app.entity.Question;
import com.examhelper.app.entity.User;
import com.examhelper.app.service.ICertificatesService;
import com.examhelper.app.service.IChapterService;
import com.examhelper.app.service.IQuestionService;
import com.examhelper.app.service.IUserService;
import com.examhelper.app.service.imp.CertificatesServiceImp;
import com.examhelper.app.service.imp.ChapterServiceImp;
import com.examhelper.app.service.imp.QuesionServiceImp;
import com.examhelper.app.service.imp.UserServiceImp;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取试题
 * Created by Administrator on 2018/7/24.
 */
public class GetQuestionHttp {
    private RequestQueue requestQueue;
    private static GetQuestionHttp getQuestionHttp;
    IChapterService chapterService;
    IQuestionService questionService;
    ICertificatesService certificatesService;
    IUserService userService;

    private GetQuestionHttp(Context context) {
        chapterService = new ChapterServiceImp(context);
        questionService = new QuesionServiceImp(context);
        certificatesService = new CertificatesServiceImp(context);
        userService = new UserServiceImp(context);
        requestQueue = Volley.newRequestQueue(context);
    }


    public static GetQuestionHttp getInstance(Context context) {
        if (getQuestionHttp == null) {
            synchronized (GetQuestionHttp.class) {
                if (getQuestionHttp == null) {
                    getQuestionHttp = new GetQuestionHttp(context);
                }
            }
        }
        return getQuestionHttp;
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
     * 根据本地最新章节ID获取服务器最新章节
     *
     * @param chapterId
     */
    private void requestNewPropertys(final int chapterId) {
        final String url = String.format(HttpConstant.API_NEW_PROPERTYS, chapterId);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
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
                        }
                        //添加到数据库
                        chapterService.addChapters(chapters);
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
     * 获取所有试题
     */
    public void requestSynthesizes() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(HttpConstant.API_ALL_SYNTHESIZES, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("GetQuestionHttp", "API:" + HttpConstant.API_ALL_SYNTHESIZES + ":response:" + response);
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
     * 根据章节ID和证数ID获取试题
     *
     * @param proId
     * @param cerId
     */
    private void requestSynthesizesById(int proId, int cerId) {
        final String url = String.format(HttpConstant.API_SYNTHESIZES_BY_ID, proId, cerId);
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
     * 登陆请求
     *
     * @param userName
     * @param password
     * @return 0 代表成功 1代表失败
     */
    public void requestLogin(String userName, String password){
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
                        userService.addUser(user);
                        //获取证书数据
                        requestCertificateById(certification.getCerId());
                    } else {
                        //TODO 登陆失败todo
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
     * 根据证书ID获取证书
     *
     * @param cerId
     */
    private void requestCertificateById(int cerId) {
        final String url = String.format(HttpConstant.API_CERTIFICATION, cerId);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("GetQuestionHttp", "API:" + url + ":response:" + response);
                try {
                    if (response.getInt("code") == 0) {
                        JSONObject data = response.getJSONObject("data");
                        Certification certification = new Gson().fromJson(data.toString(), Certification.class);
                        certificatesService.addCertificates(certification);
                    } else {

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

}
