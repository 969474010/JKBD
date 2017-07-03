package com.example.administrator.jkbd.Activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.jkbd.ExamApplication;
import com.example.administrator.jkbd.R;
import com.example.administrator.jkbd.bean.Exam;
import com.example.administrator.jkbd.bean.ExamInfo;
import com.example.administrator.jkbd.biz.ExamBiz;
import com.example.administrator.jkbd.biz.IExamBiz;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2017/6/29.
 */

public class ExamActivity extends AppCompatActivity {
    TextView tvExamInfo,tvExamTitle,tvOp1,tvOp2,tvOp3,tvOp4;
    LinearLayout layoutloading;
    ImageView mImageView;
    IExamBiz biz;

    boolean isLoadExamInfo=false;
    boolean isLoadQuestion=false;

    boolean isLoadingExamInfo=false;
    boolean isLoadingQuestion=false;

    LoadExamBroadcast mLoadExamBrodcast;
    LoadQuestionBroadcast mLoadQuestionBroadcast;


    
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_exam);
        mLoadExamBrodcast=new LoadExamBroadcast();
        mLoadQuestionBroadcast=new LoadQuestionBroadcast();
        setListecer();
        initView();
        loadData();
    }



    private void setListecer() {
        registerReceiver(mLoadExamBrodcast,new IntentFilter(ExamApplication.LOAD_EXAM_INFO));
        registerReceiver(mLoadQuestionBroadcast,new IntentFilter(ExamApplication.LOAD_EXAM_QUESTION));
    }


    private void loadData() {
        biz=new ExamBiz();
        new Thread(new Runnable() {
            @Override
            public void run() {

                biz.beginExam();
            }
        }).start();
    }

    private void initView() {
        layoutloading= (LinearLayout) findViewById(R.id.layout_loading);
        tvExamInfo=(TextView) findViewById(R.id.tv_examinfo);
        tvExamTitle=(TextView) findViewById(R.id.tv_exam_title);
        tvOp1=(TextView) findViewById(R.id.tv_op1);
        tvOp2=(TextView) findViewById(R.id.tv_op2);
        tvOp3=(TextView) findViewById(R.id.tv_op3);
        tvOp4=(TextView) findViewById(R.id.tv_op4);
        mImageView=(ImageView) findViewById(R.id.im_exam_image);
    }

    private void initData() {
        if (isLoadExamInfo&&isLoadQuestion)
        {
            ExamInfo examInfo = ExamApplication.getInstance().getmExamInfo();
            if(examInfo!=null)
            {
                showData(examInfo);
            }

            List<Exam> examList= ExamApplication.getInstance().getmExamList();
            if(examList!=null)
            {
                showExam(examList);
            }

        }

    }

    private void showExam(List<Exam> examList) {
        Exam exam=examList.get(0);
        if(exam!=null)
        {
            tvExamTitle.setText(exam.getQuestion());
            tvOp1.setText(exam.getItem1());
            tvOp2.setText(exam.getItem2());
            tvOp3.setText(exam.getItem3());
            tvOp4.setText(exam.getItem4());
            Picasso.with(ExamActivity.this)
                    .load(exam.getUrl())
                    .into(mImageView);
        }
    }


    private void showData(ExamInfo examInfo) {

        tvExamInfo.setText(examInfo.toString());
    }


    //创建内部类  下载考试信息
    class LoadExamBroadcast extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent) {
            Boolean issuccess= intent.getBooleanExtra(ExamApplication.LOAD_DATA_SUCCESS,false);
            Log.e("LoadExamBroadcast","LoadExamBroadcast,issuccess="+issuccess);
            if(issuccess)
            {
                isLoadExamInfo=true;
            }
            isLoadingExamInfo=true;
            initData();
        }
    }

    //  创建内部类判断试题是否下载成功
    class LoadQuestionBroadcast extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent) {
            Boolean issuccess= intent.getBooleanExtra(ExamApplication.LOAD_DATA_SUCCESS,false);
            Log.e("LoadQuestionBroadcast","LoadQuestionBroadcast,issuccess="+issuccess);
            if(issuccess)
            {
                isLoadQuestion=true;
            }
            isLoadingQuestion=true;
            initData();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLoadExamBrodcast!=null)
        {
            unregisterReceiver(mLoadExamBrodcast);
        }
        if (mLoadQuestionBroadcast!=null)
        {
            unregisterReceiver(mLoadQuestionBroadcast);
        }
    }
}
