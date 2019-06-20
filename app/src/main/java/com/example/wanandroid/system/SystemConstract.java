package com.example.wanandroid.system;


import com.example.wanandroid.base.IBaseCollBack;
import com.example.wanandroid.base.IBasePresenter;
import com.example.wanandroid.base.IBaseView;

public interface SystemConstract {
    interface SystemView extends IBaseView<Systempresenter> {
        void  getSystemListData(SystemDataBean dataBean);
    }

    interface Systempresenter extends IBasePresenter<SystemView> {
                void  getSystemListData();
    }
    interface  SystemFacty {
        void  getSystemListData(IBaseCollBack callBack);
    }
}
