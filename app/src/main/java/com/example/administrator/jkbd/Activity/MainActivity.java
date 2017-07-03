package com.example.administrator.jkbd.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.administrator.jkbd.R;
import com.example.administrator.jkbd.Utils.OkHttpUtils;
import com.example.administrator.jkbd.bean.ExamInfo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //进入随机测试
    public void text(View view) {
        startActivity(new Intent(MainActivity.this,ExamActivity.class));
    }

    //退出应用
    public void Exit(View view) {
        finish();
    }


}
