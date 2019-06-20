package com.example.wanandroid.navi.mvp;

import com.example.wanandroid.base.IBaseCollBack;
import com.example.wanandroid.navi.NaviApi;
import com.example.wanandroid.navi.bean.NaviBean;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NaviModule implements NaviContract.INaviModule {

    @Override
    public void getNavi(final IBaseCollBack<NaviBean> iBaseCollBack) {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(NaviApi.naviUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        NaviApi naviApi = retrofit.create(NaviApi.class);
        Observable<NaviBean> navi = naviApi.getNavi();
        navi.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NaviBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NaviBean naviBean) {
                        iBaseCollBack.onSuccess(naviBean);
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
