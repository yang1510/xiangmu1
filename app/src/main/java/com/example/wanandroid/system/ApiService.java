package com.example.wanandroid.system;


import com.example.wanandroid.project.bean.project_bean.DataBean;
import com.example.wanandroid.project.bean.project_bean.Project_Bean;
import com.example.wanandroid.project.bean.project_bean.project_chlider_bean.Project_list_bean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    String url = "https://www.wanandroid.com/";


     @GET("tree/json")
     Observable<SystemDataBean> SystemDataList();


//https://www.wanandroid.com/article/list/0/json?cid=60
     @GET("article/list/0/json?")
     Observable<System_Clild> getClild(@Query("cid")int cid);


     String project = "http://www.wanandroid.com/";

     @GET("project/tree/json")
     Observable<Project_Bean<List<DataBean>>> getProject();



     @GET("project/list/{page}/json")
     Observable<Project_list_bean> getProjectchlider(@Path("page") int page, @Query("cid") int cid);
}
