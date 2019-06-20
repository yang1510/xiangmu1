package com.example.wanandroid.official.mvp;

import com.example.wanandroid.base.IBaseCollBack;
import com.example.wanandroid.official.bean.DataListBean;
import com.example.wanandroid.official.bean.TabBean;

public class OfficialPresenter implements OfficialContract.IOfficialPresenter {

    OfficialContract.IOfficialModule module;
    OfficialContract.IOfficialView view;

    public OfficialPresenter() {
        module=new OfficialModule();
    }

    @Override
    public void getTab() {
        if (module!=null){
            module.getTab(new IBaseCollBack<TabBean>() {
                @Override
                public void onSuccess(TabBean data) {
                    if (view!=null){
                        view.getTabYes(data);
                    }
                }

                @Override
                public void onFail(String msg) {
                    if (view!=null){
                        view.getTabNo(msg);
                    }
                }
            });
        }

    }

    @Override
    public void getData() {
        if (module!=null){
            module.getData(new IBaseCollBack<DataListBean>() {
                @Override
                public void onSuccess(DataListBean data) {
                    if (view!=null){
                        view.getDataListYes(data);
                    }
                }

                @Override
                public void onFail(String msg) {
                    if (view!=null){
                        view.getDaraListNo(msg);
                    }
                }
            });
        }

    }

    @Override
    public void attachView(OfficialContract.IOfficialView view1) {
            view=view1;
    }

    @Override
    public void detachView(OfficialContract.IOfficialView view1) {
        view=view1;
    }
}
