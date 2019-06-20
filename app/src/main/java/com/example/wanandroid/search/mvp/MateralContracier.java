package com.example.wanandroid.search.mvp;

import com.example.wanandroid.base.IBaseCollBack;
import com.example.wanandroid.base.IBasePresenter;
import com.example.wanandroid.base.IBaseView;
import com.example.wanandroid.search.bean.AlertionDataBaen;
import com.example.wanandroid.search.bean.SearchBean;


public interface MateralContracier {
    public interface InavigationView extends IBaseView<Inavigationpaserenter> {
        void OnSuccess(AlertionDataBaen navStricleBaen);

        void OnFild(String str);

        void OnSouonSuccess(SearchBean souBaen);

        void OnSouFild(String str);

    }

    public interface Inavigationpaserenter extends IBasePresenter<InavigationView> {
        void getnavigation(String mater);

        void getSou();
    }

    public interface Inavigationmodel {
        void getnavifation(String mater, IBaseCollBack<AlertionDataBaen> iBaseCollBack);

        void getSou(IBaseCollBack<SearchBean> iBaseCollBack);


    }
}
