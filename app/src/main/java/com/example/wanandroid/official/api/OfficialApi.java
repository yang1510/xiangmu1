package com.example.wanandroid.official.api;

import com.example.wanandroid.official.bean.DataListBean;
import com.example.wanandroid.official.bean.TabBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface OfficialApi {

//    https://wanandroid.com/wxarticle/chapters/json
    public String taburl="https://wanandroid.com/";
    @GET("wxarticle/chapters/json")
    Observable<TabBean> getTab();


    //https://wanandroid.com/wxarticle/list/408/1/json

    public String url="https://wanandroid.com/";
    @GET("wxarticle/list/{id}/1/json")
    Observable<DataListBean> getData(@Path("id") int paga);

    //https://www.wanandroid.com/lg/uncollect_originId/2333/json
    public String quxiao="https://www.wanandroid.com/";


}
