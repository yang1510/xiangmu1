package com.example.wanandroid.search.mvp;


import com.example.wanandroid.base.IBaseCollBack;
import com.example.wanandroid.search.bean.AlertionDataBaen;
import com.example.wanandroid.search.bean.SearchBean;

public class MateralPaserenter implements MateralContracier.Inavigationpaserenter {
    private MateralContracier.InavigationView mInavigationView;
    private final MateralModel mNavigationModel;

    public MateralPaserenter() {
        mNavigationModel = new MateralModel();
    }

    @Override
    public void getnavigation(String mater) {
        mNavigationModel.getnavifation(mater, new IBaseCollBack<AlertionDataBaen>() {
            @Override
            public void onSuccess(AlertionDataBaen data) {
                if (mInavigationView != null) {
                    mInavigationView.OnSuccess(data);
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    @Override
    public void getSou() {
        mNavigationModel.getSou(new IBaseCollBack<SearchBean>() {
            @Override
            public void onSuccess(SearchBean data) {
                if (mInavigationView != null) {
                    mInavigationView.OnSouonSuccess(data);
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    @Override
    public void attachView(MateralContracier.InavigationView view) {
        mInavigationView = view;
    }

    @Override
    public void detachView(MateralContracier.InavigationView view) {
        mInavigationView = null;
    }
}
