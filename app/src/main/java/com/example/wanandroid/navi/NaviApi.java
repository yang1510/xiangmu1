package com.example.wanandroid.navi;

import com.example.wanandroid.navi.bean.NaviBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface NaviApi {
    //https://www.wanandroid.com/navi/json

    public String naviUrl="https://www.wanandroid.com/";
    @GET("navi/json")
    Observable<NaviBean> getNavi();
}
