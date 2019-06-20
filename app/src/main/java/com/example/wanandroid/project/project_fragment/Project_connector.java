package com.example.wanandroid.project.project_fragment;

import com.example.wanandroid.base.IBasePresenter;
import com.example.wanandroid.base.IBaseView;
import com.example.wanandroid.project.bean.project_bean.DataBean;
import com.example.wanandroid.project.callresult.Callresult;
import com.trello.rxlifecycle2.components.support.RxFragment;

import java.util.List;

public interface Project_connector {
    public interface Project_view extends IBaseView<Project_present> {
         void Onsuccessful(List<DataBean> bean);
         void OnFaild(String ViewFaild);
    }

    public interface Project_present extends IBasePresenter<Project_view> {
        void Project_getData();
    }
    public interface  Project_model{
        void Project_getModelData(RxFragment fragment, Callresult<List<DataBean>> callresult);
    }

}
