package com.example.wanandroid.fragment;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wanandroid.R;
import com.example.wanandroid.navi.MyNaviAdapter;
import com.example.wanandroid.navi.bean.NaviBean;
import com.example.wanandroid.navi.mvp.NaviContract;
import com.example.wanandroid.navi.mvp.NaviPresenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.TabView;

/**
 * A simple {@link Fragment} subclass.
 */
public class NaviFragment extends Fragment implements View.OnClickListener , NaviContract.INaviView {


    private RecyclerView navi_rl;
    private VerticalTabLayout navi_tab;
    private ArrayList<NaviBean.DataBean> dataBeans;
    private MyNaviAdapter myNaviAdapter;
    private LinearLayoutManager manager;
    NaviContract.INaviPresenter presenter;

    public NaviFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_navi, container, false);
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        navi_tab = inflate.findViewById(R.id.navi_tab);
        navi_rl = inflate.findViewById(R.id.navi_rl);
        manager = new LinearLayoutManager(getContext());
        navi_rl.setLayoutManager(manager);
        dataBeans = new ArrayList<>();
        myNaviAdapter = new MyNaviAdapter(getContext(), dataBeans);
        navi_rl.setAdapter(myNaviAdapter);
        navi_rl.setOnClickListener(this);
        //连动
        navi_rl.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                navi_tab.setTabSelected(manager.findFirstVisibleItemPosition());
            }
        });
        //tab和rl连动
        navi_tab.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tab, int position) {
                manager.scrollToPositionWithOffset(position,0);
            }

            @Override
            public void onTabReselected(TabView tab, int position) {

            }
        });
        setPresenter(new NaviPresenter());
        presenter.getNavi();
        FloatingActionButton floating = getActivity().findViewById(R.id.floating);
        floating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navi_rl.smoothScrollToPosition(0);
                myNaviAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onSucceed(NaviBean naviBean) {
        final List<NaviBean.DataBean> data = naviBean.getData();
        navi_tab.setTabAdapter(new TabAdapter() {
            @Override
            public int getCount() {
                return data.size();
            }

            @Override
            public ITabView.TabBadge getBadge(int position) {
                return null;
            }

            @Override
            public ITabView.TabIcon getIcon(int position) {
                return null;
            }

            @Override
            public ITabView.TabTitle getTitle(int position) {
                return  new ITabView.TabTitle.Builder()
                        .setContent(data.get(position).getName())
                        .setTextColor(Color.RED,Color.GRAY)
                        .build();

            }

            @Override
            public int getBackground(int position) {
                return 0;
            }
        });
        dataBeans.addAll(data);
        myNaviAdapter.setList(dataBeans);
        myNaviAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFail(String msg) {

    }

    @Override
    public void setPresenter(NaviContract.INaviPresenter presenter1) {
        presenter=presenter1;
        presenter.attachView(this);
    }

    @Override
    public Context getContentObject() {
        return null;
    }
}
