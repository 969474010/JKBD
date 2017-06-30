package com.example.administrator.jkbd.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/29.
 */

public class Result
{
    /**
     * error_code : 0
     * reason : ok
     * result : ["eww"]
     */

    private int error_code;
    private String reason;
    private List<Exam> result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public static List<Exam> getResult() {
        return result;
    }

    public void setResult(List<Exam> result) {
        this.result = result;
    }
}
