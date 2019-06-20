package com.example.wanandroid.base;

public interface IBaseCollBack <T>{
    void onSuccess(T data);
    void onFail(String msg);

}
