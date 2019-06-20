package com.example.wanandroid.official.mvp;

import com.example.wanandroid.base.IBaseCollBack;
import com.example.wanandroid.base.IBasePresenter;
import com.example.wanandroid.base.IBaseView;
import com.example.wanandroid.official.bean.DataListBean;
import com.example.wanandroid.official.bean.TabBean;

public interface OfficialContract {

    public interface IOfficialView extends IBaseView<IOfficialPresenter>{
        void getTabYes(TabBean tabLayout);
        void getTabNo(String msg);
        void getDataListYes(DataListBean dataListBean);
        void getDaraListNo(String msg);
    }

    public  interface IOfficialPresenter extends IBasePresenter<IOfficialView>{
        void getTab();
        void getData();
    }

    public interface  IOfficialModule{
        void getTab(IBaseCollBack<TabBean> iBaseCollBack);
        void getData(IBaseCollBack<DataListBean> iBaseCollBack);


    }

}
