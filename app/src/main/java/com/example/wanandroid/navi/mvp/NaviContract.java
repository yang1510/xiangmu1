package com.example.wanandroid.navi.mvp;

import com.example.wanandroid.base.IBaseCollBack;
import com.example.wanandroid.base.IBasePresenter;
import com.example.wanandroid.base.IBaseView;
import com.example.wanandroid.navi.bean.NaviBean;

public interface NaviContract {
    public interface INaviView extends IBaseView<INaviPresenter>{
        void onSucceed(NaviBean naviBean);
        void onFail(String msg);
    }

    public interface INaviPresenter extends IBasePresenter<INaviView>{
        void getNavi();
    }

    public interface INaviModule{
        void getNavi(IBaseCollBack<NaviBean> iBaseCollBack);

    }
}
