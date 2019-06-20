package com.example.wanandroid.shoucang.login.okhttp.interceptor;

import android.text.TextUtils;

import com.example.wanandroid.shoucang.AppConstant;
import com.example.wanandroid.shoucang.utils.SPUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/*
 * created by taofu on 2019-06-11
 **/
public class AddHeaderInterceptor implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        String requestUrl = request.url().toString();

        Request.Builder builder = request.newBuilder();

        if(requestUrl.contains(AppConstant.WEB_SITE_COLLECTIONS) || requestUrl.contains(AppConstant.WEB_SITE_UNCOLLECTIONS)
               || requestUrl.contains(AppConstant.WEB_SITE_ARTICLE)){

            String cookies = SPUtils.getValue(AppConstant.LoginParamsKey.SET_COOKIE_KEY);

            if(!TextUtils.isEmpty(cookies)){
                builder.addHeader(AppConstant.LoginParamsKey.COOKIE, cookies);
            }
        }

        return chain.proceed(builder.build());

    }



}
