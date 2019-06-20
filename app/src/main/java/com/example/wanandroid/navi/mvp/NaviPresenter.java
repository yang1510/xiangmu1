package com.example.wanandroid.navi.mvp;

import com.example.wanandroid.base.IBaseCollBack;
import com.example.wanandroid.navi.bean.NaviBean;

public class NaviPresenter implements NaviContract.INaviPresenter {
    NaviContract.INaviModule module;
    NaviContract.INaviView view;

    public NaviPresenter() {
        this.module = new NaviModule();
    }


    @Override
    public void getNavi() {
        if (module!=null){
            module.getNavi(new IBaseCollBack<NaviBean>() {
                @Override
                public void onSuccess(NaviBean data) {
                    if (view!=null){
                        view.onSucceed(data);
                    }
                }

                @Override
                public void onFail(String msg) {
                    if (view!=null){
                        view.onFail(msg);
                    }
                }
            });
        }
    }

    @Override
    public void attachView(NaviContract.INaviView view1) {
            view=view1;
    }

    @Override
    public void detachView(NaviContract.INaviView view1) {
            view=view1;
    }
}
