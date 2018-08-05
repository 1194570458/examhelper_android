package com.examhelper.app.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.examhelper.app.R;
import com.examhelper.app.entity.Chapter;
import com.examhelper.app.entity.Question;
import com.examhelper.app.listener.ChaptersToDetailListener;
import com.j256.ormlite.dao.ForeignCollection;

import java.util.ArrayList;
import java.util.List;

public class ChaptersShowAdapter extends BaseAdapter {
    private Context context;
    private List<Chapter>  chapters;
    private List<Question> questions;


    public ChaptersShowAdapter(Context context,  List<Chapter>  chapters) {
        this.context = context;
        this.chapters = chapters;
        questions=new ArrayList<Question>();
    }

    @Override
    public int getCount() {
        return chapters.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_wrongbook_lv, null);
        }
        TextView textView = convertView.findViewById(R.id.my_error_item_name);
        RelativeLayout rl_wrong = convertView.findViewById(R.id.rl_wrong);
        textView.setText(position+1+"."+chapters.get(position).getChapterName());
        ForeignCollection<Question> foreignCollection = chapters.get(position).getQuestions();
        for (Question question : foreignCollection) {
            questions.add(question);
        }
        rl_wrong.setOnClickListener(new ChaptersToDetailListener(context, questions,position));
        return convertView;
    }
}
