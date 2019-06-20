package com.example.wanandroid.login;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginApi {
    String project = "https://www.wanandroid.com/";

    //登陆
    @FormUrlEncoded
    @POST("user/login")
    Observable<Register_Bean> getLogin(@Field("username")String username, @Field("password")String password);


    // 注册
    // username,password,repassword
    @POST("user/register")
    @FormUrlEncoded
    Observable<Register_Bean> getRegister(@Field("username") String username,@Field("password") String password,@Field("repassword") String repassword);



}
