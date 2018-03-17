package com.zqf.myservice;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * -----------------------------
 * Created by zqf on 2018/3/18.
 * ---------------------------
 */

public class MyService extends Service {
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
        return super.onStartCommand(intent, flags, startId);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("onBind");
        return new MyBinder();
    }


    @Override
    public void unbindService(ServiceConnection conn) {
        System.out.println("unbindService");
        super.unbindService(conn);
    }

    @Override
    public void onDestroy() {
        System.out.println("onDestroy");
        super.onDestroy();
    }


    static class MyBinder extends Binder{
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
}
