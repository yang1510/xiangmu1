package com.example.wanandroid.project.project_fragment.priject_childer_fragment;


import com.example.wanandroid.project.bean.project_bean.project_chlider_bean.Project_list_bean;
import com.example.wanandroid.project.callresult.Callresult;
import com.example.wanandroid.system.ApiService;
import com.trello.rxlifecycle2.components.support.RxFragment;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Project_chlider_Model implements Project_chlider_connector.Project_childer_Model {

    @Override
    public void Project_model_data(int page, int cid, RxFragment fragment, final Callresult<Project_list_bean> callresult) {
        Retrofit build = new Retrofit.Builder()
                .baseUrl(ApiService.project)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Observable<Project_list_bean> projectchlider = build.create(ApiService.class).getProjectchlider(page, cid);
        projectchlider.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Project_list_bean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Project_list_bean project_list_bean) {
                callresult.Onsuccessful(project_list_bean);
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
