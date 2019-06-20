package com.example.wanandroid.project.project_fragment.priject_childer_fragment;

import com.example.wanandroid.project.bean.project_bean.project_chlider_bean.Project_list_bean;
import com.example.wanandroid.project.callresult.Callresult;
import com.trello.rxlifecycle2.components.support.RxFragment;


public class Project_chlider_present implements Project_chlider_connector.Project_childer_present {

    private Project_chlider_connector.Project_childer_view view1;
    private Project_chlider_connector.Project_childer_Model project_chlider_model;


    @Override
    public void Project_Present_childer_data() {
        project_chlider_model = new Project_chlider_Model();
        int getpage = view1.getpage();
        int getcid = view1.getcid();
        project_chlider_model.Project_model_data(getpage,getcid,(RxFragment) view1, new Callresult<Project_list_bean>() {
            @Override
            public void Onsuccessful(Project_list_bean bean) {
                view1.Onsuccessful(bean);
            }
            @Override
            public void OnFaild(String msg) {
                view1.OnFailder(msg);
            }
        });
    }



    @Override
    public void attachView(Project_chlider_connector.Project_childer_view view) {
        view1 = view;
    }

    @Override
    public void detachView(Project_chlider_connector.Project_childer_view view) {

    }
}
