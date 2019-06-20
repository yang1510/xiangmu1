package com.example.wanandroid.search;

import com.example.wanandroid.search.bean.AlertionDataBaen;
import com.example.wanandroid.search.bean.SearchBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface SearchApi {
    //https://www.wanandroid.com/hotkey/json
    public String searchUrl="https://www.wanandroid.com/";
    @GET("hotkey/json")
    Observable<SearchBean> getSearch();

    public String matleralmul="https://www.wanandroid.com/";
    @POST("article/query/0/json")
    @FormUrlEncoded
    Observable<AlertionDataBaen> getmateralDATA(@Field("k") String k);
}
