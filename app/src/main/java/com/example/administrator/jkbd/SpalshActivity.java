package com.example.administrator.jkbd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

/**
 * Created by Administrator on 2017/6/28.
 */

public class SpalshActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashactivity);


    }
    CountDownTimer timer=new CountDownTimer(3000,1000)
    {
        @Override
        public void onTick(long millisUntilFinished)
        {

        }

        @Override
        public void onFinish()
        {
            Intent intent=new Intent(SpalshActivity.this,MainActivity.class);
            startActivity(intent);

        }


    };

}
