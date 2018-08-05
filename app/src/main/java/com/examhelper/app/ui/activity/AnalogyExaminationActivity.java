package com.examhelper.app.ui.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.examhelper.app.R;
import com.examhelper.app.adapter.ExaminationSubmitAdapter;
import com.examhelper.app.adapter.PopupAdapter;
import com.examhelper.app.constant.EventBusMessageConstant;
import com.examhelper.app.constant.IntentFlagConstant;
import com.examhelper.app.constant.NormalConstant;
import com.examhelper.app.entity.Question;
import com.examhelper.app.listener.ExaminationViewPagerListener;
import com.examhelper.app.messageevent.ChangeTVEvent;
import com.examhelper.app.messageevent.NotifyBackDialogEvent;
import com.examhelper.app.service.IQuestionService;
import com.examhelper.app.service.imp.QuesionServiceImp;
import com.examhelper.app.ui.view.CountdownTextView;
import com.examhelper.app.ui.view.NotifyBackDialog;
import com.examhelper.app.ui.view.SubmitDialog;
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
    private CountdownTextView countdownTV;
    private LinearLayout collectionLayout;
    private LinearLayout activity_prepare_test_totalLayout;
    private LinearLayout ll_time;
    private LinearLayout ll_wrongbook;
    private TextView totalTv;
    private ImageView collectionIMG;
    private Boolean isShowItem = true;
    public ViewPager viewPager;
    private PopupWindow popupWindow;
    private ExaminationSubmitAdapter pagerAdapter;


    List<Question> questions = new ArrayList<Question>();
    private int rightTopicNums;// 错题数
    private int questionAcount;//试题总数
    private int score;//总分
    private int scoreStandard = 100;//一百分制
    private int pagePosition = 0;//当前页面位置
    private String isPerfectData = "1";// 是否完善资料0完成 1未完成
    public String pattern;
    private boolean isUpload = false;
    private String exmaTime = "15:00";//考试时间

    IQuestionService questionService;
    Dialog submitDialog;


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
        submitDialog = new SubmitDialog(this);
        questionService = new QuesionServiceImp(this);
        if ((List<Question>) getIntent().getSerializableExtra(IntentFlagConstant.GET_QUESTIONS) != null) {
            questions = (List<Question>) getIntent().getSerializableExtra(IntentFlagConstant.GET_QUESTIONS);
        } else {
            questions = (List<Question>) getIntent().getSerializableExtra(IntentFlagConstant.GET_WRONG_QUESTIONS);
        }
        questionAcount = questions.size();
        pattern = getIntent().getStringExtra(IntentFlagConstant.PATTERN_TITLE);
    }

    //初始化View
    public void initView() {
        leftIv = (ImageView) findViewById(R.id.left);
        collectionIMG = (ImageView) findViewById(R.id.activity_prepare_test_collectionIMG);
        titleTv = (TextView) findViewById(R.id.title);
        countdownTV = (CountdownTextView) findViewById(R.id.cuntdown_TV);
        totalTv = (TextView) findViewById(R.id.activity_prepare_test_totalTv);
        collectionIMG = (ImageView) findViewById(R.id.activity_prepare_test_collectionIMG);
        collectionLayout = (LinearLayout) findViewById(R.id.activity_prepare_test_collectionLayout);
        collectionLayout.setOnClickListener(this);
        activity_prepare_test_totalLayout = findViewById(R.id.activity_prepare_test_totalLayout);
        activity_prepare_test_totalLayout.setOnClickListener(this);
        ll_wrongbook = findViewById(R.id.ll_wrongbook);
        ll_wrongbook.setOnClickListener(this);
        ll_time = findViewById(R.id.ll_time);
        leftIv.setOnClickListener(this);
        totalTv.setText("1 /" + questionAcount);
        titleTv.setText(pattern);
        viewPager = findViewById(R.id.vote_submit_viewpager);
        viewPager.setOnPageChangeListener(new ExaminationViewPagerListener() {
            @Override
            public void onPageSelected(int position) {
                pagePosition = position;
                totalTv.setText((position + 1) + "/" + questionAcount);
                judgeIsCollection(questions.get(position));
            }
        });
        initViewPagerScroll();
        judgeIsCollection(questions.get(0));
        //如果是模拟考试将进行考试时间倒计时
        if (pattern.equals(getResources().getString(R.string.simulation_test))) {
            ll_time.setVisibility(View.VISIBLE);
            countdownTV.setText(exmaTime);
        } else {
            ll_time.setVisibility(View.GONE);
            // TODO 不是考试模式todo
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
        if (getIntent().getIntExtra(IntentFlagConstant.GET_WRONG_POSITION, -1) != -1) {
            int WRONG_POSITION = getIntent().getIntExtra(IntentFlagConstant.GET_WRONG_POSITION, -1);
            viewPager.setCurrentItem(WRONG_POSITION);
        }

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


    // 统计分析
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void uploadExamination(Integer eventBusMessageConstant) {
        if (eventBusMessageConstant == EventBusMessageConstant.COUNTING_SCORE) {
            for (Question question : questions) {
                if (question.isWrong()) {
                    rightTopicNums++;
                }
            }
            score = scoreStandard / questionAcount * (rightTopicNums);
            EventBus.getDefault().post(EventBusMessageConstant.COUNTING_END);
            //TODO 上传服务器
        }
    }

    // 弹出对话框通知用户是否退出
    @Subscribe(threadMode = ThreadMode.MAIN)
    protected void showTimeOutDialog(NotifyBackDialogEvent notifyBackDialogEvent) {
        NotifyBackDialog notifyBackDialog = new NotifyBackDialog(this, notifyBackDialogEvent.getType());
        notifyBackDialog.show();
    }

    // 弹出对话框通知用户提交成功
    @Subscribe(threadMode = ThreadMode.MAIN)
    protected void showSubmitDialog(Integer eventBusMessageConstant) {
        if (eventBusMessageConstant == EventBusMessageConstant.COUNTING_END)
            submitDialog.show();
    }


    //设置倒计时TextView内容和颜色
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changCountdownTextView(ChangeTVEvent changeTVEvent) {
        if (changeTVEvent.getColor() != 0) {
            countdownTV.setTextColor(changeTVEvent.getColor());
        }
        countdownTV.setText(changeTVEvent.getContentText());
    }

    //改变收藏图标
    public void judgeIsCollection(Question question) {
        if (question.isCollect()) {
            collectionIMG.setImageResource(R.mipmap.collection_yellow);
        } else {
            collectionIMG.setImageResource(R.mipmap.collection_white);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            NotifyBackDialogEvent notifyBackDialogEvent = new NotifyBackDialogEvent(NotifyBackDialogEvent.IS_END);
            EventBus.getDefault().post(notifyBackDialogEvent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onDestroy() {
        countdownTV.stopTime();
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_prepare_test_collectionLayout: {
                //添加收藏
                Question question = questions.get(pagePosition);
                if (question.isCollect()) {
                    question.setCollect(false);
                } else {
                    question.setCollect(true);
                }
                judgeIsCollection(question);
                questionService.updateQuestion(question);
                break;
            }
            case R.id.ll_wrongbook: {
                //错题本
                Intent intent = new Intent(this, WrongAndCollectionsActivity.class);
                intent.putExtra(IntentFlagConstant.IS_WRONGORCOLLECT, NormalConstant.WRONG_BOOK);
                startActivity(intent);
                break;
            }
            case R.id.left: {
                EventBus.getDefault().post(new NotifyBackDialogEvent(NotifyBackDialogEvent.IS_END));
                break;
            }
            case R.id.activity_prepare_test_totalLayout: {
                if (isShowItem) {
                    //显示下拉内容
                    isShowItem = false;
                    View view = LayoutInflater.from(this).inflate(R.layout.popup_gv, null);
                    GridView gridView = view.findViewById(R.id.gv_show);
                    popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, false);
                    gridView.setAdapter(new PopupAdapter(this, questionAcount, viewPager, popupWindow));
                    popupWindow.setBackgroundDrawable(new BitmapDrawable());
                    popupWindow.setOutsideTouchable(true);
                    float y = activity_prepare_test_totalLayout.getY();
                    popupWindow.showAsDropDown(activity_prepare_test_totalLayout);

                } else {
                    //关闭
                    isShowItem = true;
                    popupWindow.dismiss();
                }

                break;
            }
        }

    }
}
