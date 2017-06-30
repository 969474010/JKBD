package com.example.administrator.jkbd.dao;

import android.util.Log;

import com.example.administrator.jkbd.ExamApplication;
import com.example.administrator.jkbd.Utils.OkHttpUtils;
import com.example.administrator.jkbd.Utils.ResultUtils;
import com.example.administrator.jkbd.bean.Exam;
import com.example.administrator.jkbd.bean.ExamInfo;
import com.example.administrator.jkbd.bean.Result;

import java.util.List;

/**
 * Created by Administrator on 2017/6/30.
 */

public class ExamDao implements IExamDao {
    @Override
    public void loadExamInfo() {
        OkHttpUtils<ExamInfo> utils=new OkHttpUtils<>(ExamApplication.getInstance());
        String uri="http://101.251.196.90:8080/JztkServer/examinfo";
        utils.url(uri)
                .targetClass(ExamInfo.class)
                .execute(new OkHttpUtils.OnCompleteListener<ExamInfo>(){

                    @Override
                    public void onSuccess(ExamInfo result) {
                        Log.e("main","result="+result);
                        ExamApplication.getInstance().setmExamInfo(result);

                    }

                    @Override
                    public void onError(String error) {
                        Log.e("main","error="+error);

                    }
                });
    }

    @Override
    public void loadQuestionLists() {

        OkHttpUtils<String> utils1=new OkHttpUtils<>(ExamApplication.getInstance());
        String url2="";
        utils1.url(url2);
        utils1.targetClass(String.class);
        utils1.execute(new OkHttpUtils.OnCompleteListener<String>() {

            @Override
            public void onSuccess(String jsonStr) {

                Result result = ResultUtils.getListResultFromJson(jsonStr);
                if(result!=null&& result.getError_code()==0)
                {
                    List<Exam> list=result.getResult();
                    if(list!=null&&list.size()>0)
                    {
                        ExamApplication.getInstance().setmExamList(list);
                    }
                }

            }

            @Override
            public void onError(String error) {
                Log.e("main","error="+error);
            }
        });
    }
}
