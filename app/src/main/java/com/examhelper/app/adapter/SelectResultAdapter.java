package com.examhelper.app.adapter;

import android.content.Context;
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
import com.examhelper.app.ui.activity.AnalogyExaminationActivity;

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
    Context mContext;

    Question question;//该题所有数据对象
    String[] select;//选择内容
    boolean isMulti;//是否为多选题

    IQuestionService questionService;

    public SelectResultAdapter(Context context, Question question, View rootView) {
        mContext = context;
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
        final ViewHolder viewHolder;
        if (convertView != null) {
            viewHolder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(rootView.getContext()).inflate(R.layout.select_answer_listview_itemview, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        //因为position从0开始，答案选择或许从1开始，故要加一
        initView(viewHolder, position+1);
        return convertView;
    }

    private void initView(final ViewHolder viewHolder, final int position) {
        viewHolder.vote_submit_select_text.setText(select[position-1]);
        //如果是错题集模式的话，直接显示正确选项和错误选项与解析
        if (((AnalogyExaminationActivity) mContext).pattern.equals(mContext.getResources().getString(R.string.WrongBook))) {
            String wrongSelect = question.getWrongSelect();
            String rightSelect = question.getResult();
            if (wrongSelect.contains(String.valueOf(position))) {
                viewHolder.vote_submit_select_image.setImageResource(R.mipmap.ic_practice_test_wrong);
                viewHolder.vote_submit_select_text.setTextColor(mContext.getResources().getColor(R.color.error));
            }
            if (rightSelect.contains(String.valueOf(position))) {
                viewHolder.vote_submit_select_image.setImageResource(R.mipmap.ic_practice_test_right);
                viewHolder.vote_submit_select_text.setTextColor(mContext.getResources().getColor(R.color.right));
            }
            explaindetail_TV.setText(question.getAnalysis());
            wrongLayout.setVisibility(View.VISIBLE);
            return;
        }

        viewHolder.vote_submit_select__layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (question.getResult().contains(String.valueOf(position))) {
                    question.setWrong(false);
                    viewHolder.vote_submit_select_image.setImageResource(R.mipmap.ic_practice_test_right);
                    viewHolder.vote_submit_select_text.setTextColor(mContext.getResources().getColor(R.color.right));
                    int currentItem = ((AnalogyExaminationActivity) mContext).viewPager.getCurrentItem();
                    ((AnalogyExaminationActivity) mContext).viewPager.setCurrentItem(currentItem + 1);
                } else {
                    viewHolder.vote_submit_select_image.setImageResource(R.mipmap.ic_practice_test_wrong);
                    viewHolder.vote_submit_select_text.setTextColor(mContext.getResources().getColor(R.color.error));
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

    }

    public static class ViewHolder {
        public View rootView;
        public ImageView vote_submit_select_image;
        public TextView vote_submit_select_text;
        public LinearLayout vote_submit_select__layout;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.vote_submit_select_image = (ImageView) rootView.findViewById(R.id.vote_submit_select_image);
            this.vote_submit_select_text = (TextView) rootView.findViewById(R.id.vote_submit_select_text);
            this.vote_submit_select__layout = (LinearLayout) rootView.findViewById(R.id.vote_submit_select__layout);
        }

    }
}
