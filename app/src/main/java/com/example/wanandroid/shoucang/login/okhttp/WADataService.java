package com.example.wanandroid.shoucang.login.okhttp;

import com.example.wanandroid.BuildConfig;
import com.example.wanandroid.shoucang.AppConstant;
import com.example.wanandroid.shoucang.login.okhttp.interceptor.AddHeaderInterceptor;
import com.example.wanandroid.shoucang.login.okhttp.interceptor.SaveCookieInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/*
 * created by taofu on 2019-06-11
 **/
public class WADataService {


    private static final long DEFAULT_TIMEOUT = 20000;


    private static volatile ApiService mApiService;

    public static ApiService getService() {

        if (mApiService == null) {
            synchronized (WADataService.class) {
                if (mApiService == null) {
                    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

                    if (BuildConfig.DEBUG) {
                        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                    } else {
                        logging.setLevel(HttpLoggingInterceptor.Level.NONE);
                    }


                    OkHttpClient httpClient = new OkHttpClient.Builder()
                            .addInterceptor(logging)
                            .addInterceptor(new SaveCookieInterceptor())
                            .addInterceptor(new AddHeaderInterceptor())
                            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                            .build();

                    Retrofit retrofit = new Retrofit.Builder()
                            .client(httpClient)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .baseUrl(AppConstant.BASE_URL)
                            .build();

                    mApiService = retrofit.create(ApiService.class);
                }
            }
        }
        return mApiService;
    }
}
