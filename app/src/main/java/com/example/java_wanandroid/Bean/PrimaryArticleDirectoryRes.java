package com.example.java_wanandroid.Bean;

import java.util.List;

public class PrimaryArticleDirectoryRes {
    private List<PrimaryArticleDirectory> data;
    private int errorCode;
    private String errorMsg;

    public List<PrimaryArticleDirectory> getData() {
        return data;
    }

    public void setData(List<PrimaryArticleDirectory> data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
