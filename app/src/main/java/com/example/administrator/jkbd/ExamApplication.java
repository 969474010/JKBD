package com.example.administrator.jkbd;

import android.app.Application;
import android.util.Log;

import com.example.administrator.jkbd.Utils.OkHttpUtils;
import com.example.administrator.jkbd.bean.Exam;
import com.example.administrator.jkbd.bean.ExamInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/6/30.
 */

public class ExamApplication extends Application {
    ExamInfo mExamInfo;
    List<Exam> mExamList;
    ExamApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        initData();
    }

    private void initData() {
        OkHttpUtils<ExamInfo> utils=new OkHttpUtils<>(instance);
        String uri="http://101.251.196.90:8080/JztkServer/examinfo";
        utils.url(uri)
                .targetClass(ExamInfo.class)
                .execute(new OkHttpUtils.OnCompleteListener<ExamInfo>(){

                    @Override
                    public void onSuccess(ExamInfo result) {
                        Log.e("main","result="+result);
                        mExamInfo=result;
                    }

                    @Override
                    public void onError(String error) {
                        Log.e("main","error="+error);

                    }
                });

    }
}
