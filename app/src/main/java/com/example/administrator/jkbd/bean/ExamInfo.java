package com.example.administrator.jkbd.bean;

/**
 * Created by Administrator on 2017/6/29.
 */

public class ExamInfo
{
    private String subjectTitle;
    private int uid;
    private int limitTime;
    private int questionCount;


    public String getSubjectTitle() {
        return subjectTitle;
    }

    public void setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(int limitTime) {
        this.limitTime = limitTime;
    }

    public int getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(int questionCount) {
        this.questionCount = questionCount;
    }

    @Override
    public String toString() {
        return
                "考试科目：" + subjectTitle + '\n' +
                "试题数目：" + questionCount + '\n' +
                "考试时间：" + limitTime +"分钟";
    }
}
