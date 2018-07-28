package com.examhelper.app.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.examhelper.app.constant.HttpConstant;
import com.examhelper.app.entity.Chapter;
import com.examhelper.app.entity.Question;
import com.examhelper.app.service.IChapterService;
import com.examhelper.app.service.IQuestionService;
import com.examhelper.app.service.imp.ChapterServiceImp;
import com.examhelper.app.service.imp.QuesionServiceImp;

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

    private GetQuestionHttp(Context context) {
        chapterService = new ChapterServiceImp(context);
        questionService = new QuesionServiceImp(context);
        requestQueue = Volley.newRequestQueue(context);
    }

    public void getData() {
        //TODO 根据本地已缓存试题数据请求服务器获取其他试题数据
        long chapterAcount = chapterService.queryCount();
        requestPropertys();//获取所有章节
        requestSynthesizes();//获取所有题目
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
     * 添加到章节表
     */
    private void requestPropertys() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(HttpConstant.API_PROPERTYS, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("GetQuestionHttp", "API:" + HttpConstant.API_PROPERTYS + ":response:" + response);
                try {
                    List<Chapter> chapters = new ArrayList<>();
                    JSONArray data = response.getJSONArray("data");
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject jsonObject = data.getJSONObject(i);
                        Chapter chapter = new Chapter(jsonObject.getInt("proId"), jsonObject.getString("chapter"));
                        Log.e("GetQuestionHttp", "chapter:" + chapter);
                        chapters.add(chapter);
                    }
                    //添加到数据库
                    chapterService.addChapters(chapters);
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
     * 添加到试题表
     */
    private void requestSynthesizes() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(HttpConstant.API_SYNTHESIZES, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("GetQuestionHttp", "API:" + HttpConstant.API_SYNTHESIZES + ":response:" + response);
                try {
                    JSONArray data = response.getJSONArray("data");
                    List<Question> questions = new ArrayList<>();
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject jsonObject = data.getJSONObject(i);
                        Chapter chapter = new Chapter();
                        chapter.setChapterId(jsonObject.getInt("proId"));
                        Question question = new Question(
                                jsonObject.getInt("synId"),
                                jsonObject.getString("title"),
                                jsonObject.getString("select"),
                                jsonObject.getString("result"),
                                jsonObject.getString("analysis"),
                                chapter,
                                jsonObject.getInt("cerId")
                        );
                        Log.e("GetQuestionHttp", "chapter:" + question);
                        questions.add(question);
                    }
                    questionService.addQuestions(questions);
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
