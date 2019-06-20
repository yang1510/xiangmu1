package com.example.wanandroid.shoucang.login.okhttp;


import com.example.wanandroid.collect.CollectBean;
import com.example.wanandroid.shoucang.AppConstant;
import com.example.wanandroid.shoucang.login.entity.HttpResult;
import com.example.wanandroid.shoucang.login.entity.User;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/*
 * created by taofu on 2019-06-11
 **/
public interface ApiService {

    @POST(AppConstant.WEB_SITE_REGISTER)
    @FormUrlEncoded
    Observable<HttpResult<User>> register(@FieldMap Map<String, String> params);

    @POST(AppConstant.WEB_SITE_LOGIN)
    @FormUrlEncoded
    Observable<HttpResult<User>> login(@FieldMap Map<String, String> params);

    //https://www.wanandroid.com/lg/collect/1165/json
    @POST("lg/collect/{id}/json")
    Observable<ResponseBody> ShouCang(@Path("id") String id);

    //https://www.wanandroid.com/lg/collect/list/0/json
    @GET("lg/collect/list/0/json")
    Observable<CollectBean> ZhanShi();

    //https://www.wanandroid.com/lg/uncollect_originId/2333/json
    @POST("lg/uncollect/{id}/json")
    @FormUrlEncoded
    Observable<ResponseBody> quxiao(@Path("id") int id, @Field("originId") int originId);
}
