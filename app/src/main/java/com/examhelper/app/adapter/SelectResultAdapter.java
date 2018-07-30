package com.examhelper.app.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.examhelper.app.R;
import com.examhelper.app.entity.Question;
import com.examhelper.app.service.IQuestionService;
import com.examhelper.app.service.imp.QuesionServiceImp;

/**
 * Created by Administrator on 2018/7/26.
 */

/**
 * 给试题选项ListView填充数据的Adapter
 */
public class SelectResultAdapter extends BaseAdapter {
    TextView explaindetail_TV;//显示题目解析内容的TextView
    LinearLayout wrongLayout;
    View rootView;//ListView的父View

    Question question;//该题所有数据对象
    String[] select;//选择内容
    boolean isMulti;//是否为多选题

    IQuestionService questionService;

    public SelectResultAdapter(Question question, View rootView) {
        this.question = question;
        this.rootView = rootView;
        explaindetail_TV = rootView.findViewById(R.id.activity_prepare_test_explaindetail);
        wrongLayout = rootView.findViewById(R.id.activity_prepare_test_wrongLayout);
        initData();
    }

    private void initData() {
        questionService = new QuesionServiceImp(rootView.getContext());
        select = question.getSelect().split(",");
        if (question.getResult().length() > 1) {
            isMulti = true;
        }
    }

    @Override
    public int getCount() {
        return select.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(rootView.getContext()).inflate(R.layout.select_answer_listview_itemview, null);
        LinearLayout linearLayout = view.findViewById(R.id.vote_submit_select__layout);
        final ImageView isRight_Img = view.findViewById(R.id.vote_submit_select_image);
        final TextView select_Tv = view.findViewById(R.id.vote_submit_select_text);
        select_Tv.setText(select[position]);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if (question.getResult().contains(String.valueOf(position))) {
                    question.setWrong(false);
                    isRight_Img.setImageResource(R.mipmap.ic_practice_test_right);
                    select_Tv.setTextColor(R.color.right);
                } else {
                    isRight_Img.setImageResource(R.mipmap.ic_practice_test_wrong);
                    select_Tv.setTextColor(R.color.error);
                    //显示试题解析
                    explaindetail_TV.setText(question.getAnalysis());
                    wrongLayout.setVisibility(View.VISIBLE);
                    // 提交错误信息
                    question.setWrong(true);
                    question.setWrongSelect(String.valueOf(position));
                    questionService.updateQuestion(question);
                }
            }
        });
        return view;
    }


}
