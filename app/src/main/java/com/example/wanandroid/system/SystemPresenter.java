package com.example.wanandroid.system;


import com.example.wanandroid.base.IBaseCollBack;

public class SystemPresenter implements SystemConstract.Systempresenter  {

    private final SystemDataModel mSystemDataModel;
SystemConstract.SystemView mView;
    public SystemPresenter() {
        mSystemDataModel = new SystemDataModel();

    }

    @Override
    public void getSystemListData() {
        mSystemDataModel.getSystemListData(new IBaseCollBack() {
            @Override
            public void onSuccess(Object data) {
                mView.getSystemListData((SystemDataBean) data);
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    @Override
    public void attachView(SystemConstract.SystemView view) {
        mView = view;
    }

    @Override
    public void detachView(SystemConstract.SystemView view) {

    }
}
