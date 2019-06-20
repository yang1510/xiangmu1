package com.example.wanandroid.base;

public interface IBasePresenter <T extends IBaseView>{
    void attachView(T view);
    void detachView(T view);

}
