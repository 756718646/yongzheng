package app.tianzhu.fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import app.tianzhu.framework.BaseFragment;
import app.tianzhu.framework.FieldView;
import app.tianzhu.framework.VLog;
import app.tianzhu.shuangying.BindPayCountActivity;
import app.tianzhu.shuangying.BusinessEditActivity;
import app.tianzhu.shuangying.BusinessMoneyActivity;
import app.tianzhu.shuangying.GetMoneyActivity;
import app.tianzhu.shuangying.GetMoneyListActivity;
import app.tianzhu.shuangying.IncomeStatisticsActivity;
import app.tianzhu.shuangying.MyAdvListActivity;
import app.tianzhu.shuangying.MyMoneyActivity;
import app.tianzhu.shuangying.R;
import app.tianzhu.shuangying.SettingActivity;
import app.tianzhu.shuangying.UserEditActivity;
import app.tianzhu.util.DataUtil;
import app.tianzhu.view.TabButtonView;

/**
 *
 * Created by Administrator on 2015/12/24.
 */
public class Me2Fragment extends BaseFragment implements View.OnClickListener {

    @FieldView(id = R.id.scollview)
    private ScrollView scrollView;

    @FieldView(id = R.id.head)
    private RelativeLayout head;


    @FieldView(id = R.id.titlebut)
    private TabButtonView titilebut;

    @FieldView(id = R.id.set)
    private ImageView set;

    @FieldView(id = R.id.wdtx_layout)
    private LinearLayout wdtx_layout;   //我的提现


    @FieldView(id = R.id.sjxx_layout)
    private LinearLayout sjxx_layout;   //商家信息

    @FieldView(id = R.id.user_layout)
    private LinearLayout user_layout;   //个人中心

    @FieldView(id = R.id.wdhb_layout)
    private LinearLayout wdhb_layout;   //我的红包

    @FieldView(id = R.id.layout_ggtf)
    private LinearLayout layout_ggtf;   //广告投放

    @FieldView(id = R.id.layout_ye)
    private LinearLayout layout_ye;     //商家余额

    @FieldView(id = R.id.layout_tj)
    private LinearLayout layout_tj;     //商家统计

    @FieldView(id = R.id.loginout)
    private LinearLayout loginout;      //退出登录


    // 记录首次按下位置
    private float mFirstPosition = 0;

    // 是否正在放大
    private Boolean mScaling = false;

    private DisplayMetrics metric;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_me2;
    }

    @Override
    public void init(View view) {

        this.titilebut.init(baseActivity);
        titilebut.setIndex(2);
        set.setOnClickListener(this);
        wdtx_layout.setOnClickListener(this);
        sjxx_layout.setOnClickListener(this);
        user_layout.setOnClickListener(this);
        wdhb_layout.setOnClickListener(this);
        layout_ggtf.setOnClickListener(this);
        layout_ye.setOnClickListener(this);
        layout_tj.setOnClickListener(this);
        loginout.setOnClickListener(this);

        // 获取屏幕宽高
        metric = new DisplayMetrics();
        baseActivity.getWindowManager().getDefaultDisplay().getMetrics(metric);

        //获取控件
        scrollView = (ScrollView) getView(R.id.scollview);
        //设置图片初始大小  这里我设为满屏的16:9
        ViewGroup.LayoutParams lp = (ViewGroup.LayoutParams) head.getLayoutParams();
        lp.width = metric.widthPixels;
        lp.height = metric.widthPixels * 9 / 16;
        head.setLayoutParams(lp);

        //监听滚动事件
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint({ "ClickableViewAccessibility", "NewApi" })
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ViewGroup.LayoutParams lp = (ViewGroup.LayoutParams) head.getLayoutParams();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        //手指离开后恢复图片
                        mScaling = false;
                        replyImage();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (!mScaling) {
                            if (scrollView.getScrollY() == 0) {
                                VLog.v("记录mFirstPosition");
                                mFirstPosition = event.getY();// 滚动到顶部时记录位置，否则正常返回
                            } else {
                                break;
                            }
                        }

                        VLog.v("滑动:"+event.getY() + "   ---   " + mFirstPosition);

                        int distance = (int) ((event.getY() - mFirstPosition) * 0.5); // 滚动距离乘以一个系数
                        VLog.v("计算distance："+distance);
                        if (distance < 0) { // 当前位置比记录位置要小，正常返回
                            VLog.v("计算distance < 0 "+distance);
                            break;
                        }
                        VLog.v("放大:"+event.getY());
                        // 处理放大
                        mScaling = true;
                        lp.width = metric.widthPixels;
                        lp.height = (metric.widthPixels + distance) * 9 / 16;
                        head.setLayoutParams(lp);
                        return true; // 返回true表示已经完成触摸事件，不再处理
                }
                return false;
            }
        });

    }

    // 回弹动画 (使用了属性动画)
    @SuppressLint("NewApi")
    public void replyImage() {
        final ViewGroup.LayoutParams lp = (ViewGroup.LayoutParams) head.getLayoutParams();
        final float w = head.getLayoutParams().width;// 图片当前宽度
        final float h = head.getLayoutParams().height;// 图片当前高度
        final float newW = metric.widthPixels;// 图片原宽度
        final float newH = metric.widthPixels * 9 / 16;// 图片原高度

        // 设置动画
        ValueAnimator anim = ObjectAnimator.ofFloat(0.0F, 1.0F).setDuration(200);

        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float cVal = (Float) animation.getAnimatedValue();
                lp.width = (int) (w - (w - newW) * cVal);
                lp.height = (int) (h - (h - newH) * cVal);
                head.setLayoutParams(lp);
            }
        });
        anim.start();

    }

    @Override
    public void getResult(Message msg) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.set:
                baseActivity.startActivity(new Intent(baseActivity,SettingActivity.class));
                break;
            case R.id.wdtx_layout:
                baseActivity.startActivity(new Intent(baseActivity, GetMoneyListActivity.class));
                break;
            case R.id.bdzh_layout:
                baseActivity.startActivity(new Intent(baseActivity, BindPayCountActivity.class));
                break;
            case R.id.sjxx_layout:
                baseActivity.startActivity(new Intent(baseActivity, BusinessEditActivity.class));
                break;
            case R.id.user_layout:
                baseActivity.startActivity(new Intent(baseActivity, UserEditActivity.class));
                break;
            case R.id.wdhb_layout:
                baseActivity.startActivity(new Intent(baseActivity,MyMoneyActivity.class));
                break;
            case R.id.layout_ggtf:
                baseActivity.startActivity(new Intent(baseActivity, MyAdvListActivity.class));
                break;
            case R.id.layout_ye:
                baseActivity.startActivity(new Intent(baseActivity, BusinessMoneyActivity.class));
                break;
            case R.id.layout_tj:
                baseActivity.startActivity(new Intent(baseActivity, IncomeStatisticsActivity.class));
                break;
            case R.id.loginout:
                DataUtil.saveUserData(null,baseActivity);
                break;
        }
    }
}
