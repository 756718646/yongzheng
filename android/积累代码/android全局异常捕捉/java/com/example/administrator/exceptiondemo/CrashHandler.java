package com.example.administrator.exceptiondemo;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Looper;
import android.util.Log;

/**
 * Created by Administrator on 2015/12/8.
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {

    public static final String TAG = "全局异常捕捉CrashHandler";
    private static CrashHandler INSTANCE = new CrashHandler();
    private Context mContext;
    private Thread.UncaughtExceptionHandler mDefaultHandler;// 系统默认的UncaughtException处理类

    private CrashHandler() {
    }

    public static CrashHandler getInstance() {
        return INSTANCE;
    }

    public void init(Context ctx) {
        System.out.println("uncaughtException-init:"+System.currentTimeMillis());
        mContext = ctx;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        // if (!handleException(ex) && mDefaultHandler != null) {
        // mDefaultHandler.uncaughtException(thread, ex);
        // } else {
        // android.os.Process.killProcess(android.os.Process.myPid());
        // System.exit(10);
        // }

        System.out.println("uncaughtException"+System.currentTimeMillis());

        boolean ishandler = handleException(ex);
        if (ishandler){
            // 自己处理
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
            return;
        }else{
            //默认处理
//            Intent intent = new Intent(mContext.getApplicationContext(), MainActivity.class);
//            PendingIntent restartIntent = PendingIntent.getActivity(
//                    mContext.getApplicationContext(), 0, intent,
//                    Intent.FLAG_ACTIVITY_NEW_TASK);
            //退出当前程序，跳转到首页 MainActivity.class
//            AlarmManager mgr = (AlarmManager)mContext.getSystemService(Context.ALARM_SERVICE);
//            mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 1000,restartIntent); // 1秒钟后重启应用

        }


    }

    private void restartApplication() {
        final Intent intent = mContext.getPackageManager().getLaunchIntentForPackage(mContext.getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mContext.startActivity(intent);
    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成. 开发者可以根据自己的情况来自定义异常处理逻辑
     *
     * @param ex
     * @return true:如果处理了该异常信息;否则返回false
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }

        Log.d(TAG, Log.getStackTraceString(ex));//打印日志堆栈
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                new AlertDialog.Builder(mContext).setTitle("提示").setCancelable(false)
                        .setMessage("程序崩溃了...").setNeutralButton("我知道了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0);//直接退出app
                    }
                })
                        .create().show();
                Looper.loop();
            }
        }.start();

        //日志收集等
        System.out.println("handleException-处理异常");

        return false;
    }

}
