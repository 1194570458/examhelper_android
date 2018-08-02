package com.examhelper.app.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.examhelper.app.R;
import com.examhelper.app.entity.Question;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 填充试题内容的Adapter
 */
public class ExaminationSubmitAdapter extends PagerAdapter {
    Context mContext;
    List<Question> questions;
    Map<Integer, View> views = new HashMap<>();

    public ExaminationSubmitAdapter(Context mContext, List<Question> questions) {
        this.mContext = mContext;
        this.questions = questions;
    }

    @Override
    public int getCount() {
        return questions.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view = views.get(position);
        if (view == null) {
            view = initView(position);
        }
        container.addView(view);
        return view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(views.get(position));
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    private View initView(int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.vote_submit_viewpager_item, null);
        TextView isOne_TV = view.findViewById(R.id.activity_prepare_test_no);
        TextView title_TV = view.findViewById(R.id.activity_prepare_test_question);
        ListView select_LV = view.findViewById(R.id.activity_prepare_test_selectLV);
        Question question = questions.get(position);
        isOne_TV.setText(R.string.one);
        title_TV.setText(question.getTitle());
        SelectResultAdapter adapter = new SelectResultAdapter(mContext, question, view);
        select_LV.setAdapter(adapter);
        views.put(position, view);
        return view;
    }
}
