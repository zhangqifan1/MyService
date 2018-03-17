package com.zqf.myservice;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * -----------------------------
 * Created by zqf on 2018/3/18.
 * ---------------------------
 */

public class MyService2 extends Service {
    /**
     * 使用Binder 模式
     */

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("onStartCommand");
        double chinese = intent.getDoubleExtra("chinese", 0);
        double math = intent.getDoubleExtra("math", 0);
        double english = intent.getDoubleExtra("english", 0);
        double v = calculatAvg(chinese, math, english);//这里可以得到值，但是无法具体返回给我们的Activity
        Intent broad = new Intent("hello.broad");
        System.out.println(v+"");
        broad.putExtra("double",v);
        sendBroadcast(broad);
        return super.onStartCommand(intent, flags, startId);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



    /**
     * 计算平均值的方法
     */
    public double calculatAvg(double ...doubles){
        int length = doubles.length;
        if(length==0){
            return 0;
        }
        int sum=0;
        for (double d:doubles) {
            sum+=d;
        }
        return sum/length;
    }
}
