package com.zqf.myservice;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Intent intent;
    private Intent intent2;
    private MyService.MyBinder service1;
    private ServiceConnection conn = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //链接成功自动调用
            service1 = (MyService.MyBinder) service;
            System.out.println("onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //链接无效调用
            System.out.println("onServiceDisconnected");
        }
    };
    private EditText edChinese;
    private EditText edMath;
    private EditText edEnglish;
    private static Button xianshi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        intent = new Intent("hello.service");
        intent2=new Intent("hello.MyService2");
        //这个是第一个Demo
        bindService(intent,conn,BIND_AUTO_CREATE);

        xianshi = findViewById(R.id.xianshi);
    }


//    public void makeSure(View view) {
//        bindService(intent,conn,BIND_AUTO_CREATE);
//    }

    private void initView() {
        edChinese = (EditText) findViewById(R.id.edChinese);
        edMath = (EditText) findViewById(R.id.edMath);
        edEnglish = (EditText) findViewById(R.id.edEnglish);
    }

    private void submit() {
        // validate
        String edChineseString = edChinese.getText().toString().trim();
        if (TextUtils.isEmpty(edChineseString)) {
            Toast.makeText(this, "edChineseString不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String edMathString = edMath.getText().toString().trim();
        if (TextUtils.isEmpty(edMathString)) {
            Toast.makeText(this, "edMathString不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String edEnglishString = edEnglish.getText().toString().trim();
        if (TextUtils.isEmpty(edEnglishString)) {
            Toast.makeText(this, "edEnglishString不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if(service1!=null){
            double v = service1.calculatAvg(Double.parseDouble(edChineseString), Double.parseDouble(edMathString), Double.parseDouble(edEnglishString));
            Toast.makeText(this, ""+v, Toast.LENGTH_SHORT).show();
        }

    }

    public void showCj(View view)
    {
        submit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }

    public void start(View view) {
        submit2();
    }


    public static class MyReceive extends BroadcastReceiver{


        @Override
        public void onReceive(Context context, Intent intent) {
            xianshi.setText(intent.getDoubleExtra("double",0)+"");
        }
    }


    private void submit2() {
        // validate
        String edChineseString = edChinese.getText().toString().trim();
        if (TextUtils.isEmpty(edChineseString)) {
            Toast.makeText(this, "edChineseString不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String edMathString = edMath.getText().toString().trim();
        if (TextUtils.isEmpty(edMathString)) {
            Toast.makeText(this, "edMathString不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String edEnglishString = edEnglish.getText().toString().trim();
        if (TextUtils.isEmpty(edEnglishString)) {
            Toast.makeText(this, "edEnglishString不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        intent2.putExtra("chinese",Double.parseDouble(edChineseString));
        intent2.putExtra("math",Double.parseDouble(edMathString));
        intent2.putExtra("english",Double.parseDouble(edEnglishString));
       startService(intent2);

    }
}
