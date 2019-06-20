package com.example.wanandroid.shoucang;

/*
 * created by taofu on 2019-06-11
 **/
public interface AppConstant {

    String BASE_URL = "https://www.wanandroid.com/";


    String WEB_SITE_LOGIN = "user/login";
    String WEB_SITE_REGISTER = "user/register";
    String WEB_SITE_COLLECTIONS = "lg/collect";
    String WEB_SITE_UNCOLLECTIONS = "lg/uncollect";
    String WEB_SITE_ARTICLE = "article";


    public interface LoginParamsKey {
        String SET_COOKIE_KEY = "set-cookie";
        String COOKIE = "Cookie";
        String USER_NAME = "username";
        String PASSWORD = "password";
        String REPASSWORD = "repassword";
    }
}
