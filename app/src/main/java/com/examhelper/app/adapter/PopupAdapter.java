package com.examhelper.app.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.examhelper.app.R;
/**
 * PopupWindow显示下拉数据的Adapter
 */
public class PopupAdapter extends BaseAdapter {
    private Context context;
    private int count;
    private ViewPager viewPager;
    private PopupWindow popupWindow;

    public PopupAdapter(Context context, int count, ViewPager viewPager, PopupWindow popupWindow) {
        this.context = context;
        this.count = count;
        this.viewPager = viewPager;
        this.popupWindow=popupWindow;
    }

    @Override
    public int getCount() {
        return count;
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
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.popup_item_tv, null);
        }
        TextView tv_pup = convertView.findViewById(R.id.tv_pup);
        tv_pup.setText(position+1 + "");
        tv_pup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(position);
                popupWindow.dismiss();
            }
        });
        return convertView;
    }
}
