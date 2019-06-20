package com.example.wanandroid.fragment;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.wanandroid.R;
import com.example.wanandroid.official.MyFragmentListAdapter;
import com.example.wanandroid.official.OfficialListFragment;
import com.example.wanandroid.official.bean.DataListBean;
import com.example.wanandroid.official.bean.TabBean;
import com.example.wanandroid.official.mvp.OfficialContract;
import com.example.wanandroid.official.mvp.OfficialPresenter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class OfficialFragment extends Fragment implements OfficialContract.IOfficialView {


    private View inflate;
    private TabLayout tab;
    private ViewPager vp;
    OfficialContract.IOfficialPresenter presenter;
    private ArrayList<Fragment> fragments;

    public OfficialFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_official, container, false);
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        tab = inflate.findViewById(R.id.tab1);
        vp = inflate.findViewById(R.id.vp);
        fragments = new ArrayList<>();
        setPresenter(new OfficialPresenter());
        presenter.getTab();


    }

    private static final String TAG = "OfficialFragment";
    @Override
    public void getTabYes(TabBean tabLayout) {
        Log.d(TAG, "getTabYes: "+tabLayout.getData().size());
        List<TabBean.DataBean> data = tabLayout.getData();
        for (int i = 0; i < data.size(); i++) {
            tab.addTab(tab.newTab().setText(data.get(i).getName()));
            fragments.add(new OfficialListFragment(data.get(i).getId()));
        }

        MyFragmentListAdapter myFragmentListAdapter = new MyFragmentListAdapter(getChildFragmentManager(), fragments);
        vp.setAdapter(myFragmentListAdapter);
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        vp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));
        //tab.setupWithViewPager(vp);

    }

    @Override
    public void getTabNo(String msg) {

    }

    @Override
    public void getDataListYes(DataListBean dataListBean) {

    }

    @Override
    public void getDaraListNo(String msg) {

    }

    @Override
    public void setPresenter(OfficialContract.IOfficialPresenter presenter1) {
        presenter=presenter1;
        presenter.attachView(this);
    }

    @Override
    public Context getContentObject() {
        return null;
    }
}
