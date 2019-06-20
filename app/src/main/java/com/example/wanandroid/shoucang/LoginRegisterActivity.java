package com.example.wanandroid.shoucang;

import android.os.Bundle;

import com.example.wanandroid.R;
import com.example.wanandroid.shoucang.base.BaseActivity;
import com.example.wanandroid.shoucang.login.LoginFragment;

public class LoginRegisterActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        addFragment(getSupportFragmentManager(), LoginFragment.class, R.id.login_fragment_container, null);
    }
}
