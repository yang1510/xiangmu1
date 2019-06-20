package com.example.wanandroid.search.mvp;

import android.util.Log;

import com.example.wanandroid.base.IBaseCollBack;
import com.example.wanandroid.search.SearchApi;
import com.example.wanandroid.search.bean.AlertionDataBaen;
import com.example.wanandroid.search.bean.SearchBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class MateralModel implements MateralContracier.Inavigationmodel {
    @Override
    public void getnavifation(String mater, final IBaseCollBack<AlertionDataBaen> iBaseCollBack) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(SearchApi.matleralmul)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofit.create(SearchApi.class).getmateralDATA(mater).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AlertionDataBaen>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AlertionDataBaen alertionDataBaen) {
                        if (alertionDataBaen != null) {
                            Log.d(TAG, "onNext:============ " + alertionDataBaen.toString());
                            iBaseCollBack.onSuccess(alertionDataBaen);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }


    @Override
    public void getSou(IBaseCollBack<SearchBean> iBaseCollBack) {

    }
}
