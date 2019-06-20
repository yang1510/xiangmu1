package com.example.wanandroid.project.project_adapter;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.wanandroid.base.BaseFragment;

import java.util.ArrayList;

public class ProjectViewPager extends FragmentPagerAdapter {


    private final ArrayList<String> project_title;
    private final ArrayList<BaseFragment> projectFragments;

    public ProjectViewPager(FragmentManager childFragmentManager, ArrayList<String> project_title, ArrayList<BaseFragment> projectFragments) {
        super(childFragmentManager);
        this.project_title = project_title;
        this.projectFragments = projectFragments;
    }

    @Override
    public Fragment getItem(int i) {
        return projectFragments.get(i);
    }

    @Override
    public int getCount() {
        return projectFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return project_title.get(position);
    }
}
