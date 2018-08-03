package com.examhelper.app.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.examhelper.app.R;
import com.examhelper.app.entity.Question;
import com.examhelper.app.listener.CollectionToDetailListener;

import java.util.List;

public class CollectionsShowAdapter extends BaseAdapter {
    private Context context;
    private List<Question> questions;

    public CollectionsShowAdapter(Context context, List<Question> questions) {
        this.context = context;
        this.questions = questions;
    }

    @Override
    public int getCount() {
        return questions.size();
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
        textView.setText(position+1+"."+questions.get(position).getTitle());
        rl_wrong.setOnClickListener(new CollectionToDetailListener(context,questions,position));
        return convertView;
    }
}
