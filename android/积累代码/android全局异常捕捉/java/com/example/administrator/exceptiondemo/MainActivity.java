package com.example.administrator.exceptiondemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * 参考资料:http://blog.csdn.net/hehe9737/article/details/7662123
 *
 *
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button but;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);  //传入参数必须为Activity，否则AlertDialog将不显示。
        // 创建错误
        but = (Button) super.findViewById(R.id.but);

        but.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        textView.setText("dddd");
    }
}
