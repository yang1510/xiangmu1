package com.example.wanandroid.shoucang;

import com.example.wanandroid.shoucang.base.BaseRepository;
import com.example.wanandroid.shoucang.base.IBaseCallBack;
import com.example.wanandroid.shoucang.login.LoginContract;
import com.example.wanandroid.shoucang.login.entity.HttpResult;
import com.example.wanandroid.shoucang.login.entity.User;
import com.example.wanandroid.shoucang.login.okhttp.ApiService;
import com.example.wanandroid.shoucang.login.okhttp.WADataService;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;


/*
 * created by taofu on 2019-06-11
 **/
public class LoginRepository extends BaseRepository implements LoginContract.ILoginSource {

    private ApiService mApiService;


    public LoginRepository(){
        mApiService = WADataService.getService();
    }
    @Override
    public void register(LifecycleProvider provider, Map<String, String> params, IBaseCallBack<User> callBack) {
        request(provider, mApiService.register(params), callBack);
    }

    @Override
    public void login(LifecycleProvider provider, Map<String, String> params, IBaseCallBack<User> callBack) {
        request(provider, mApiService.login(params), callBack);
    }


    private void request(LifecycleProvider provider, Observable<HttpResult<User>> observable, IBaseCallBack<User> callBack){
        observer(provider, observable,new Function<HttpResult<User>, ObservableSource<User>>() {
            @Override
            public ObservableSource<User> apply(HttpResult<User> userHttpResult) throws Exception {
                if(userHttpResult.errorCode == 0 && userHttpResult.data != null){
                    return Observable.just(userHttpResult.data);
                }

                return Observable.error(new NullPointerException(userHttpResult.errorMsg));
            }
        },callBack);
    }
}
