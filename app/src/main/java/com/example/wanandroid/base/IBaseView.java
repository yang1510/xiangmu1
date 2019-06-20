package com.example.wanandroid.base;

import android.content.Context;

public interface IBaseView <T extends IBasePresenter> {
    //添加对p层的引用
    void setPresenter(T presenter);
    Context getContentObject();
}
