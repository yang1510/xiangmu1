package com.example.wanandroid.base;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.FragmentManager;

import com.example.wanandroid.R;
import com.trello.rxlifecycle2.components.support.RxFragment;

public class BaseFragment extends RxFragment {

    private BaseActivity baseActivity;

    public int enter() {
        return R.anim.common_page_right_in;
    }

    public int exit() {
        return R.anim.common_page_right_out;
    }

    public int popEnter() {
        return R.anim.common_page_left_in;
    }

    public int popExit() {
        return R.anim.common_page_left_out;
    }

    protected boolean isNeedToAddBackStack() {
        return true;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof BaseActivity){
            baseActivity = (BaseActivity) activity;
        }
    }

    protected void addFragment(FragmentManager manager, Class<? extends BaseFragment> aClass, int containerId, Bundle args){
        if (baseActivity!=null){
            baseActivity.addFragment(manager,aClass,containerId,args);
        }
    }

//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        if (activity instanceof BaseActivity){
//            mBaseActivity= (BaseActivity) activity;
//        }
//    }

//    protected void addFragment(FragmentManager manager, Class<? extends BaseFragment> aClass, int containerId, Bundle args){
//        if (mBaseActivity!=null){
//            mBaseActivity.addFragment(manager,aClass,containerId,args);
//        }
//    }
}
