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
        doHttp(HttpConstant.API_GET_ALL_CHAPTER);//获取所有章节
        doHttp(HttpConstant.API_GET_ALL_QUESTION);//获取所有题目
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

    public void doHttp(final String url) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("GetQuestionHttp", "API:" + url + ":response:" + response);
                insertIntoDb(url, response);
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

    private void insertIntoDb(String url, JSONObject response) {
        try {
            if (response.getInt("code") == 0) {
                JSONArray data = response.getJSONArray("data");
                switch (url) {
                    case HttpConstant.API_GET_ALL_CHAPTER: {
                        insertChapter(data);
                        break;
                    }
                    case HttpConstant.API_GET_ALL_QUESTION: {
                        insertQuestion(data);
                        break;
                    }
                }
            } else {
                Log.e("GetQuestionHttp", "insertIntoDb: API请求异常");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加到章节表
     *
     * @param data
     */
    private void insertChapter(JSONArray data) {
        try {
            List<Chapter> chapters = new ArrayList<>();
            for (int i = 0; i < data.length(); i++) {
                JSONObject jsonObject = data.getJSONObject(i);
                Chapter chapter = new Chapter(jsonObject.getInt("proId"), jsonObject.getString("chapter"));
                Log.e("GetQuestionHttp", "chapter:" + chapter);
                chapters.add(chapter);
            }
            chapterService.addChapters(chapters);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    /**
     * 添加到试题表
     *
     * @param data
     */
    private void insertQuestion(JSONArray data) {
        try {
            List<Question> questions = new ArrayList<>();
            for (int i = 0; i < data.length(); i++) {
                JSONObject jsonObject = data.getJSONObject(i);
                Chapter chapter = new Chapter();
                chapter.setChapterId(jsonObject.getInt("proId"));
                Question question = new Question(
                        jsonObject.getString("title"),
                        jsonObject.getString("select"),
                        jsonObject.getInt("result"),
                        jsonObject.getString("analysis"),
                        chapter,
                        jsonObject.getInt("cerId")
                );
                Log.e("GetQuestionHttp", "chapter:" + chapter);
                questions.add(question);
            }
            questionService.addQuestions(questions);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
