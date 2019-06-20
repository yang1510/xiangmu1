package com.example.wanandroid.project;

import com.example.wanandroid.BuildConfig;
import com.example.wanandroid.project.callresult.Callresult;
import com.example.wanandroid.system.ApiService;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.components.support.RxFragment;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class BeanService {
    public static volatile ApiService apiService;

    public static ApiService getApiService(String baseurl) {
        if (apiService == null) {
            synchronized (BeanService.class) {
                if (apiService == null) {
                    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                    if (BuildConfig.DEBUG) {
                        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                    } else {
                        interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
                    }
                    OkHttpClient okHttpClient = new OkHttpClient.Builder()
                            .addInterceptor(interceptor)
                            .build();
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(baseurl)
                            .client(okHttpClient)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .build();
                    apiService = retrofit.create(ApiService.class);

                }
            }
        }
        return apiService;
    }

    public static <T, R> void getModelData(RxFragment fragment, Observable<T> observer, Function<T, ObservableSource<R>> function, final Callresult<R> callresult) {
        observer.flatMap(function).unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .compose(fragment.<R>bindUntilEvent(FragmentEvent.DESTROY))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<R>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(R r) {
                        callresult.Onsuccessful(r);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callresult.OnFaild(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
