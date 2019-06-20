package com.example.wanandroid.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.example.wanandroid.R;
import com.example.wanandroid.base.BaseFragment;
import com.example.wanandroid.project.bean.project_bean.DataBean;
import com.example.wanandroid.project.project_adapter.ProjectViewPager;
import com.example.wanandroid.project.project_fragment.Project_connector;
import com.example.wanandroid.project.project_fragment.Project_present;
import com.example.wanandroid.project.project_fragment.priject_childer_fragment.PorjectChilderFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class PrecjetFragment extends BaseFragment implements Project_connector.Project_view {

    private TabLayout mPrjectTab;
    private ViewPager mProjectViewpager;
    private Project_connector.Project_present presnet;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_project, null);
        initView(inflate);
        setPresenter(new Project_present());
        return inflate;

    }

    private void initView(View inflate) {
        mPrjectTab = (TabLayout) inflate.findViewById(R.id.tab);
        mProjectViewpager = (ViewPager) inflate.findViewById(R.id.vp);
    }
    @Override
    public void Onsuccessful(List<DataBean> bean) {
        ArrayList<String> project_title = new ArrayList<>();
        ArrayList<BaseFragment> projectFragments = new ArrayList<>();
        for (int i = 0; i < bean.size(); i++) {
            PorjectChilderFragment porjectChilderFragment = new PorjectChilderFragment();
            int id = bean.get(i).getId();
            project_title.add(bean.get(i).getName());
            Bundle bundle = new Bundle();
            bundle.putInt("cid",id);
            porjectChilderFragment.setArguments(bundle);
            projectFragments.add(porjectChilderFragment);
        }
        ProjectViewPager viewPagerAdapter = new ProjectViewPager(getChildFragmentManager(),project_title,projectFragments);
        mProjectViewpager.setAdapter(viewPagerAdapter);
        mPrjectTab.setupWithViewPager(mProjectViewpager);

    }

    @Override
    public void OnFaild(String ViewFaild) {

    }

    @Override
    public void setPresenter(Project_connector.Project_present presenter) {
        presnet = presenter;
        presnet.attachView(this);
        presnet.Project_getData();
    }

    @Override
    public Context getContentObject() {
        return null;
    }

}
