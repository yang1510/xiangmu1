package com.example.wanandroid.home.mvp;

import com.example.wanandroid.base.IBaseCollBack;
import com.example.wanandroid.home.bean.BannerBean;
import com.example.wanandroid.home.bean.DaBean;

public class HomePresenter implements HomeContact.IHomePresenter {

    HomeContact.IHomeModule module;
    HomeContact.IHomeView view;
    public HomePresenter() {
        module=new HomeModule();
    }

    @Override
    public void getData(int paga) {
        if (module!=null){
            module.getData(paga,new IBaseCollBack<DaBean>() {
                @Override
                public void onSuccess(DaBean data) {
                    if (view!=null){
                        view.onDaYes(data);
                    }
                }

                @Override
                public void onFail(String msg) {
                    if (view!=null){
                        view.onDaNo(msg);
                    }
                }
            });
        }
    }

    @Override
    public void getBanner() {
        if (module!=null){
            module.getBanner(new IBaseCollBack<BannerBean>() {
                @Override
                public void onSuccess(BannerBean data) {
                    if (view!=null){
                        view.onBannerYes(data);
                    }
                }

                @Override
                public void onFail(String msg) {
                    if (view!=null){
                        view.onBannerNo(msg);
                    }
                }
            });
        }
    }


    @Override
    public void attachView(HomeContact.IHomeView view1) {
        view=view1;
    }

    @Override
    public void detachView(HomeContact.IHomeView view1) {
        view=view1;
    }
}
