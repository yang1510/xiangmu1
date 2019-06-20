package com.example.wanandroid.project.project_fragment;

import com.example.wanandroid.project.BeanService;
import com.example.wanandroid.project.bean.project_bean.DataBean;
import com.example.wanandroid.project.bean.project_bean.Project_Bean;
import com.example.wanandroid.project.callresult.Callresult;
import com.example.wanandroid.system.ApiService;
import com.trello.rxlifecycle2.components.support.RxFragment;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;


public class Project_model implements Project_connector.Project_model {
    @Override
    public void Project_getModelData(RxFragment fragment, Callresult<List<DataBean>> callresult) {
        BeanService.getModelData(fragment, BeanService.getApiService(ApiService.project).getProject(), new Function<Project_Bean<List<DataBean>>, ObservableSource<List<DataBean>>>() {
            @Override
            public ObservableSource<List<DataBean>> apply(Project_Bean<List<DataBean>> dataBeans) throws Exception {
                if(dataBeans.data.size()>0 && dataBeans!=null){
                return Observable.just(dataBeans.data);
                }else{
                    return  Observable.error(new NullPointerException("数据为空"));
                }
            }
        }, callresult);
    }
}
