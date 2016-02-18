package cn.zhaiyifan.interestingtitlebar.demo;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class MyDemoActivity extends Activity implements ObservableScrollView.ScrollViewListener {

    private LinearLayout title;
    private ObservableScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_demo);

        this.scrollView = (ObservableScrollView) super.findViewById(R.id.scrollView);
        this.title = (LinearLayout) super.findViewById(R.id.title);
        title.getBackground().setAlpha(0);
        this.scrollView.setScrollViewListener(this);

//        this.scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
//            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
//            @Override
//            public void onScrollChanged() {
//                int y = (int) scrollView.getScrollY();
//                y = y/10;
//                if (y>247)y = 247;
//                if (y<0) y = 0;
//
//                title.getBackground().setAlpha(y);
//
//            }
//        });

    }

    @Override
    public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
        y = y/2;
        if (y>247)y = 247;
        if (y<0) y = 0;
        title.getBackground().setAlpha(y);
    }
}
