package com.example.wanandroid.shoucang.login;

import com.example.wanandroid.shoucang.base.IBaseCallBack;
import com.example.wanandroid.shoucang.base.IBasePresenter;
import com.example.wanandroid.shoucang.base.IBaseView;
import com.example.wanandroid.shoucang.login.entity.User;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.Map;



/*
 * created by taofu on 2019-06-11
 **/
public interface LoginContract {



    public interface ILoginView extends IBaseView<ILoginPresenter> {

        void onSuccess(User user);

        void onFail(String msg);
    }


    public interface ILoginPresenter extends IBasePresenter<ILoginView> {

        void register(String username, String password, String repassword);
        void login(String username, String password);

    }


    public interface ILoginSource{

        void register(LifecycleProvider provider, Map<String, String> params, IBaseCallBack<User> callBack);

        void login(LifecycleProvider provider, Map<String, String> params, IBaseCallBack<User> callBack);

    }

}
