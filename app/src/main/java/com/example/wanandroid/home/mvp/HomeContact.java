package com.example.wanandroid.home.mvp;

import com.example.wanandroid.base.IBaseCollBack;
import com.example.wanandroid.base.IBasePresenter;
import com.example.wanandroid.base.IBaseView;
import com.example.wanandroid.home.bean.BannerBean;
import com.example.wanandroid.home.bean.DaBean;

public interface HomeContact {
    //契约类
    public  interface IHomeView extends IBaseView<IHomePresenter>{
        void onDaYes(DaBean bean);
        void onBannerYes(BannerBean bannerBean);
        void onDaNo(String msg);
        void onBannerNo(String msg);

    }

    public  interface IHomePresenter extends IBasePresenter<IHomeView>{
        void getData(int paga);
        void getBanner();
    }

    public  interface IHomeModule{
        void getBanner(IBaseCollBack<BannerBean> iBaseCollBack);
        void getData(int paga,IBaseCollBack<DaBean> iBaseCollBack);
    }
}
