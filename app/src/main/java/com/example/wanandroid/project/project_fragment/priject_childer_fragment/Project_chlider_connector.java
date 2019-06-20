package com.example.wanandroid.project.project_fragment.priject_childer_fragment;

import com.example.wanandroid.base.IBasePresenter;
import com.example.wanandroid.base.IBaseView;
import com.example.wanandroid.project.bean.project_bean.project_chlider_bean.Project_list_bean;
import com.example.wanandroid.project.callresult.Callresult;
import com.trello.rxlifecycle2.components.support.RxFragment;



public interface Project_chlider_connector {
    public interface Project_childer_view extends IBaseView<Project_childer_present> {
        void Onsuccessful(Project_list_bean bean_childer);
        void OnFailder(String msg);
        int getpage();
        int getcid();
    }

    public interface Project_childer_present extends IBasePresenter<Project_childer_view> {
        void Project_Present_childer_data();
    }

    public interface Project_childer_Model {
        void Project_model_data(int page, int cid, RxFragment fragment, Callresult<Project_list_bean> callresult);
    }
}
