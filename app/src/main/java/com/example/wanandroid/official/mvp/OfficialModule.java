package com.example.wanandroid.official.mvp;

import com.example.wanandroid.base.IBaseCollBack;
import com.example.wanandroid.official.api.OfficialApi;
import com.example.wanandroid.official.bean.DataListBean;
import com.example.wanandroid.official.bean.TabBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class OfficialModule implements OfficialContract.IOfficialModule{
    @Override
    public void getTab(final IBaseCollBack<TabBean> iBaseCollBack) {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(OfficialApi.taburl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        OfficialApi officialApi = retrofit.create(OfficialApi.class);
        officialApi.getTab().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TabBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TabBean tabBean) {
                        iBaseCollBack.onSuccess(tabBean);
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

    @Override
    public void getData(IBaseCollBack<DataListBean> iBaseCollBack) {

    }
}
