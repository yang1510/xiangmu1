package com.example.wanandroid.base;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Toast;

import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.wanandroid.R;
import com.example.wanandroid.util.LoadingPage;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.List;

public class BaseActivity extends RxAppCompatActivity {
    LoadingPage mLoadingPage;
    protected void addFragment(FragmentManager manager, Class<? extends BaseFragment> aClass, int containerId, Bundle args){
            String tag=aClass.getName();
        Fragment fragment = manager.findFragmentByTag(tag);
        FragmentTransaction transaction = manager.beginTransaction();//开启事务
        if (fragment==null){//没有添加
            try {
                fragment=aClass.newInstance();
                BaseFragment baseFragment= (BaseFragment) fragment;//强转成我们的baseFragment
                //设置fragment进入，退出，弹进，弹出动画
                transaction.setCustomAnimations(baseFragment.enter(),baseFragment.exit(),baseFragment.popEnter(),baseFragment.popExit());
                transaction.add(containerId,fragment);
                if (baseFragment.isNeedToAddBackStack()){//判断是否需要加入回退栈
                    transaction.addToBackStack(tag);//加入回退栈时定制一个tag，以便再找到指定的事务
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }

        }else{
            if (fragment.isAdded()){
                if (fragment.isHidden()){
                    transaction.show(fragment);
                }
            }else{
                transaction.add(containerId,fragment);
            }
        }
        if (fragment!=null){
            fragment.setArguments(args);
            hideBeforeFragment(manager,transaction,fragment);
            transaction.commit();
        }
    }
//除当前fragment以外的所有fragment进行隐藏
    private void hideBeforeFragment(FragmentManager manager, FragmentTransaction transaction, Fragment fragment) {
        List<Fragment> fragments = manager.getFragments();
        for (Fragment fragment1:fragments) {
            if (fragment1!=fragment&&fragment1.isHidden()){
                transaction.hide(fragment1);
            }
        }
    }



    protected  void showLoadingPage(int type){
        showLoadingPage(android.R.id.content,type);
    }

    protected  void showLoadingPage(int containerId, int type){
        showLoadingPage((ViewGroup)findViewById(containerId),type);
    }

    protected void  showLoadingPage(ViewGroup group,int type){
        if (mLoadingPage==null){
            mLoadingPage = (LoadingPage) LayoutInflater.from(this).inflate(R.layout.layout_laoding_page, null, false);
        }
        group.addView(mLoadingPage);
    }
    protected void dismiss(){
        if (mLoadingPage!=null) {
            ViewParent parent=mLoadingPage.getParent();
            if (parent!=null) {
                ViewGroup group= (ViewGroup) parent;
                group.removeView(mLoadingPage);
            }
        }
    }

    protected void onError(String msg){
        onError(null,msg);
    }

    protected void onError(LoadingPage.OnReloadCallBack callBack,String msg){
        if(mLoadingPage != null){
            mLoadingPage.onError(callBack, msg);
        }
    }

    protected void onError(){
        if(mLoadingPage != null){
            mLoadingPage.onError();
        }
    }

    protected void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    protected void showToast(@StringRes int rId) {
        Toast.makeText(this, rId, Toast.LENGTH_SHORT).show();
    }

}
