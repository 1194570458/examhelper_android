package com.examhelper.app.ui.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.examhelper.app.R;
import com.examhelper.app.adapter.ExaminationSubmitAdapter;
import com.examhelper.app.constant.IntentFlagConstant;
import com.examhelper.app.entity.Question;
import com.examhelper.app.messageevent.ChangeTVEvent;
import com.examhelper.app.service.IQuestionService;
import com.examhelper.app.service.imp.QuesionServiceImp;
import com.examhelper.app.ui.view.CountdownTextView;
import com.examhelper.app.ui.view.IsTimeDialog;
import com.examhelper.app.ui.view.VoteSubmitViewPager;
import com.examhelper.app.utils.ViewPagerScroller;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 答题
 *
 * @author JR
 */
public class AnalogyExaminationActivity extends Activity implements OnClickListener {

    private ImageView leftIv;
    private TextView titleTv;
    private CountdownTextView right;
    private LinearLayout upLayout;
    private LinearLayout collectionLayout;
    private LinearLayout nextLayout;
    private TextView totalTv;
    private ImageView collectionIMG;
    VoteSubmitViewPager viewPager;
    ExaminationSubmitAdapter pagerAdapter;

    List<Question> questions = new ArrayList<Question>();
    private String pageCode;
    private int pageScore;
    private int errortopicNums;// 错题数
    private int errortopicNums1;// 错题数
    private int questionAcount;//试题总数
    private int pagePosition = 0;//当前页面位置
    private String isPerfectData = "1";// 是否完善资料0完成 1未完成
    private boolean isExma = false;// false模拟 true竞赛
    private boolean isUpload = false;
    private String exmaTime = "15:00";//考试时间

    IQuestionService questionService;


    Dialog SubmitDialog;


    private Handler handlerSubmit = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);

            switch (msg.what) {
                case 1:
                    showSubmitDialog();
                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            SubmitDialog.dismiss();
                            finish();
                        }
                    }, 3000);
                    break;
                default:
                    break;
            }

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_practice_test);
        EventBus.getDefault().register(this);
        initData();
        initView();
        loadData();
    }

    private void initData() {
        questionService = new QuesionServiceImp(this);
        questions = (List<Question>) getIntent().getSerializableExtra(IntentFlagConstant.GET_QUESTIONS);
        isExma = getIntent().getBooleanExtra(IntentFlagConstant.IS_EXMA, false);
        questionAcount = questions.size();
    }

    //初始化View
    public void initView() {
        leftIv = (ImageView) findViewById(R.id.left);
        collectionIMG = (ImageView) findViewById(R.id.activity_prepare_test_collectionIMG);
        titleTv = (TextView) findViewById(R.id.title);
        right = (CountdownTextView) findViewById(R.id.right);
        totalTv = (TextView) findViewById(R.id.activity_prepare_test_totalTv);
        collectionIMG = (ImageView) findViewById(R.id.activity_prepare_test_collectionIMG);
        upLayout = (LinearLayout) findViewById(R.id.activity_prepare_test_upLayout);
        collectionLayout = (LinearLayout) findViewById(R.id.activity_prepare_test_collectionLayout);
        nextLayout = (LinearLayout) findViewById(R.id.activity_prepare_test_nextLayout);
        collectionLayout.setOnClickListener(this);
        upLayout.setOnClickListener(this);
        nextLayout.setOnClickListener(this);
        totalTv.setText("0 /" + questionAcount);
        viewPager = (VoteSubmitViewPager) findViewById(R.id.vote_submit_viewpager);
        leftIv.setOnClickListener(this);
        initViewPagerScroll();
        //如果是模拟考试将进行考试时间倒计时
        if (isExma) {
            right.setTime(exmaTime);
            titleTv.setText(R.string.exam);
            Drawable drawable1 = getBaseContext().getResources().getDrawable(
                    R.mipmap.ic_practice_time);
            drawable1.setBounds(0, 0, drawable1.getMinimumWidth(),
                    drawable1.getMinimumHeight());
            right.setVisibility(View.VISIBLE);
            right.setCompoundDrawables(drawable1, null, null, null);
            right.setText(exmaTime);
        } else {
            titleTv.setText(R.string.answer);
        }
    }

    //装载数据
    private void loadData() {
        pagerAdapter = new ExaminationSubmitAdapter(
                AnalogyExaminationActivity.this,
                questions);
        viewPager.setAdapter(pagerAdapter);
        viewPager.getParent()
                .requestDisallowInterceptTouchEvent(false);
    }


    //设置ViewPager的滑动速度
    private void initViewPagerScroll() {
        try {
            Field mScroller = null;
            mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            ViewPagerScroller scroller = new ViewPagerScroller(viewPager.getContext());
            mScroller.set(viewPager, scroller);
        } catch (NoSuchFieldException e) {

        } catch (IllegalArgumentException e) {

        } catch (IllegalAccessException e) {

        }
    }


    // 提交试卷
    public void uploadExamination() {
        //TODO 提交试卷
    }

    // 弹出对话框通知用户答题时间到
    protected void showTimeOutDialog(String backtype) {
        IsTimeDialog isTimeDialog = new IsTimeDialog(this, backtype);
        isTimeDialog.show();
    }

    // 弹出对话框通知用户提交成功
    protected void showSubmitDialog() {
        SubmitDialog = new Dialog(this, R.style.dialog);
        SubmitDialog.setContentView(R.layout.my_dialog);
        TextView title = (TextView) SubmitDialog.findViewById(R.id.dialog_title);
        TextView content = (TextView) SubmitDialog.findViewById(R.id.dialog_content);
        content.setText("提交成功，感谢您的参与!");
        final Button confirm_btn = (Button) SubmitDialog
                .findViewById(R.id.dialog_sure);
        confirm_btn.setVisibility(View.GONE);
        Button cancel_btn = (Button) SubmitDialog.findViewById(R.id.dialog_cancle);
        cancel_btn.setVisibility(View.GONE);
        SubmitDialog.setCanceledOnTouchOutside(false);// 设置点击Dialog外部任意区域关闭Dialog
        SubmitDialog.setOnKeyListener(new OnKeyListener() {

            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                //TODO
                return true;
            }
        });
        SubmitDialog.show();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changCountdownTextView(ChangeTVEvent changeTVEvent) {
        if (changeTVEvent.getColor() != 0) {
            right.setTextColor(changeTVEvent.getColor());
        }
        right.setText(changeTVEvent.getContentText());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
//            TODO 监听返回键
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onDestroy() {
        Log.d("AnalogyExaminationActiv", "onDestroy");
        right.stopTime();
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        Question question = questions.get(pagePosition);
        switch (v.getId()) {
            case R.id.activity_prepare_test_upLayout: {
                pagePosition = pagePosition - 1;
                //防止越界
                if (pagePosition < 0) {
                    pagePosition = 0;
                    return;
                }
                viewPager.setCurrentItem(pagePosition);
                totalTv.setText((pagePosition + 1) + "/" + questionAcount);
                break;
            }
            case R.id.activity_prepare_test_nextLayout: {
                pagePosition = pagePosition + 1;
                //防止越界
                if (pagePosition == questionAcount) {
                    pagePosition = questionAcount - 1;
                    return;
                }
                viewPager.setCurrentItem(pagePosition);
                totalTv.setText((pagePosition + 1) + "/" + questionAcount);
                break;
            }
            case R.id.activity_prepare_test_collectionLayout: {
                //添加收藏
                question.setCollect(true);
                questionService.addQuestion(question);
                break;
            }
        }
        if (question.isCollect()) {
            collectionIMG.setImageResource(R.mipmap.collection_yellow);
        } else {
            collectionIMG.setImageResource(R.mipmap.collection_white);
        }
    }
}
