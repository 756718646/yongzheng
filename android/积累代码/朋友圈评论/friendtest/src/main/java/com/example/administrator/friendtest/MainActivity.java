package com.example.administrator.friendtest;

/**
 * 事件橱窗
 * * http://www.cnblogs.com/tiantianbyconan/p/3823429.html
 */

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView text;
    private String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.text = (TextView) super.findViewById(R.id.text);
        str = "球队[呼赛测试球队3]回复[球员2]加入球asdfasdfasdfafdsdfasdfasdfsdfasdfasdfasdfadsfasdfafda";
        setTitle("朋友圈");
        //setTitleColor(Color.parseColor("#ffffff"));
        text.setText(getClickableSpan("name1", "name2", "2014年3月14日 - 而这个TextView的text使用了ClickableSpan,当点击ClickableSpan指向的内容时,会执行...我们通过BackgroundSpan来设置背景颜色。 那贴上我们加上selector效果后MyTextView..."));
        //此行必须有设置超链接为可点击状态
        text.setMovementMethod(LinkMovementMethod.getInstance());

        text.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                System.out.println("text触摸:" + event.getAction());
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    text.setBackgroundColor(Color.parseColor("#E4E4E4"));
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    text.setBackgroundColor(Color.parseColor("#EEEEEE"));
                } else {
                    text.setBackgroundColor(Color.parseColor("#E4E4E4"));
                }

                return false;
            }
        });

        text.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(MainActivity.this,"222222",Toast.LENGTH_SHORT).show();
                return true;

            }
        });
        text.setClickable(true);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"1111111",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private SpannableString getClickableSpan(String name1,String name2,String content) {

        if(content==null||content.isEmpty()){
            content = " ";
        }else{
            content = " "+content;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(name1);
        sb.append("回复");
        sb.append(name2);
        sb.append(":");
        sb.append(content);

        //监听器
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Click Success", Toast.LENGTH_SHORT).show();
            }
        };

        NoUnderlineSpan mNoUnderlineSpan = new NoUnderlineSpan();//这句话的目的是去除特殊文字下划线效果
        ForegroundColorSpan color = new ForegroundColorSpan(Color.parseColor("#576B95"));//文字颜色
        // BackgroundColorSpan span = new BackgroundColorSpan(R.drawable.bg_text); //背景颜色

        SpannableString spanableInfo = new SpannableString(sb.toString());

        int start = 0;  //超链接起始位置
        int end = name1.length();   //超链接结束位置

        int start2 = end + 2;	//第二段
        int end2 = start2 + name2.length();

        int start3 = end2 + 1;//第三段
        int end3 = end2 + content.length();//第四段

        //名字1
//        spanableInfo.setSpan(new Clickable(listener), start, end, Spanned.SPAN_COMPOSING);
//        spanableInfo.setSpan(new NoUnderlineSpan(), start, end, Spanned.SPAN_COMPOSING);
//        spanableInfo.setSpan(color, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        spanableInfo.setSpan(new Clickable(listener), start, end, Spanned.SPAN_COMPOSING);
        spanableInfo.setSpan(new Touchable(),start, end, Spanned.SPAN_COMPOSING);
        spanableInfo.setSpan(new NoUnderlineSpan(), start, end, Spanned.SPAN_COMPOSING);
        spanableInfo.setSpan(new ForegroundColorSpan(Color.parseColor("#576B95")), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        // spanableInfo.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        //spanableInfo.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //名字2
        spanableInfo.setSpan(new Clickable(listener), start2, end2, Spanned.SPAN_COMPOSING);
        spanableInfo.setSpan(new NoUnderlineSpan(), start2, end2, Spanned.SPAN_COMPOSING);
        spanableInfo.setSpan(new ForegroundColorSpan(Color.parseColor("#576B95")), start2, end2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //spanableInfo.setSpan(span, start2, end2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //内容
       // spanableInfo.setSpan(new Clickable(listener), start3, end3, Spanned.SPAN_MARK_MARK);
      //  spanableInfo.setSpan(new NoUnderlineSpan(), start3, end3, Spanned.SPAN_COMPOSING);
     //   spanableInfo.setSpan(new ForegroundColorSpan(Color.parseColor("#454545")), start3, end3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        spanableInfo.setSpan(new BackgroundColorSpan(Color.parseColor("#EEEEEE")), start3, end3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);




        return spanableInfo;
    }

    class NoUnderlineSpan extends UnderlineSpan {
        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(ds.linkColor);
            ds.bgColor = 0x00eeeeee;
            ds.setUnderlineText(false);
        }
    }


    class  Touchable extends ClickableSpan implements View.OnTouchListener{

        @Override
        public void onClick(View widget) {

        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            System.out.println("点击Touch");
            return false;
        }


        @Override
        public void updateDrawState(TextPaint ds) {

            ds.setColor(Color.argb(255, 54, 92, 124));

          //  ds.setColor(Color.parseColor("#576B95"));
            ds.setUnderlineText(false);
        }
    }

    class Clickable extends ClickableSpan implements View.OnClickListener {
        private final View.OnClickListener mListener;

        public Clickable(View.OnClickListener listener) {
            mListener = listener;
        }

        @Override
        public void onClick(View view) {
            mListener.onClick(view);
        }
    }
}
