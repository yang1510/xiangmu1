package com.example.wanandroid.home.api;

import com.example.wanandroid.Out;
import com.example.wanandroid.home.bean.BannerBean;
import com.example.wanandroid.home.bean.DaBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Home_Api {

    //https://www.wanandroid.com/banner/json    banner
    //https://www.wanandroid.com/article/list/1/json

    public  String BannerUrl="https://www.wanandroid.com/";
    @GET("banner/json")
    Observable<BannerBean> getBanner();


    public String DataUrl="https://www.wanandroid.com/";
    @GET("article/list/{id}/json")
    Observable<DaBean> getData(@Path("id")int id);


    //https://www.wanandroid.com/user/logout/json
    public String outUrl="https://www.wanandroid.com/";
    @GET("user/logout/json")
    Observable<Out> getOut();
}
