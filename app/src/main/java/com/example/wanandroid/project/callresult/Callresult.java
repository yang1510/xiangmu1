package com.example.wanandroid.project.callresult;

public interface Callresult<T > {
     void Onsuccessful(T bean);
     void OnFaild(String msg);
}
