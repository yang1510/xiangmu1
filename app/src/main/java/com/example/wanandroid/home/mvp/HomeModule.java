package com.example.wanandroid.home.mvp;

import com.example.wanandroid.base.IBaseCollBack;
import com.example.wanandroid.home.api.Home_Api;
import com.example.wanandroid.home.bean.BannerBean;
import com.example.wanandroid.home.bean.DaBean;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeModule implements HomeContact.IHomeModule  {


    @Override
    public void getBanner(final IBaseCollBack<BannerBean> iBaseCollBack) {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Home_Api.BannerUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        Home_Api home_api = retrofit.create(Home_Api.class);
        Observable<BannerBean> banner = home_api.getBanner();
        banner.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BannerBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BannerBean bannerBean) {
                        iBaseCollBack.onSuccess(bannerBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iBaseCollBack.onFail(e
                        .getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Override
    public void getData(int paga,final IBaseCollBack<DaBean> iBaseCollBack) {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Home_Api.DataUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        Home_Api home_api = retrofit.create(Home_Api.class);
        Observable<DaBean> data = home_api.getData(paga);
        data.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DaBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DaBean bean) {
                        iBaseCollBack.onSuccess(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iBaseCollBack.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
