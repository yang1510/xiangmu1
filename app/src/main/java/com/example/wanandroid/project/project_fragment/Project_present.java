package com.example.wanandroid.project.project_fragment;


import com.example.wanandroid.project.bean.project_bean.DataBean;
import com.example.wanandroid.project.callresult.Callresult;
import com.trello.rxlifecycle2.components.support.RxFragment;

import java.util.List;


public class Project_present implements Project_connector.Project_present {

    private Project_connector.Project_view view1;
    private Project_connector.Project_model project_model;


    @Override
    public void Project_getData() {
        project_model = new Project_model();
        project_model.Project_getModelData((RxFragment) view1, new Callresult<List<DataBean>>() {
            @Override
            public void Onsuccessful(List<DataBean> bean) {
                view1.Onsuccessful(bean);
            }

            @Override
            public void OnFaild(String msg) {
                view1.OnFaild(msg);
            }
        });
    }


    @Override
    public void attachView(Project_connector.Project_view view) {
        view1 = view;
    }

    @Override
    public void detachView(Project_connector.Project_view view) {

    }
}
