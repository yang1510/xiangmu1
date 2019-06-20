package com.example.wanandroid.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wanandroid.R;
import com.example.wanandroid.base.BaseFragment;
import com.example.wanandroid.system.SystemActivity;
import com.example.wanandroid.system.SystemConstract;
import com.example.wanandroid.system.SystemDataBean;
import com.example.wanandroid.system.SystemPresenter;
import com.example.wanandroid.system.adapter.SystemRecAapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class SystemFragment extends BaseFragment implements SystemConstract.SystemView {
    private RecyclerView mSystemRec;
    private SystemConstract.Systempresenter mPresenter;
    private SystemRecAapter systemRecAapter;
    private ArrayList<Integer> ids;
    private ArrayList<String> strings;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_system, null);
        initView(inflate);
        setPresenter(new SystemPresenter());
        return inflate;
    }

    private void initView(View inflate) {
        mSystemRec = (RecyclerView) inflate.findViewById(R.id.systemRec);
        FloatingActionButton floating = getActivity().findViewById(R.id.floating);
        floating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSystemRec.smoothScrollToPosition(0);
            }
        });

    }

    private static final String TAG = "SystemFragment";
    @Override
    public void getSystemListData(final SystemDataBean dataBean) {
        if (dataBean != null) {
            final List<SystemDataBean.DataBean> data = dataBean.getData();
            systemRecAapter = new SystemRecAapter(data, getContext());
            mSystemRec.setAdapter(systemRecAapter);
            mSystemRec.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
            mSystemRec.setLayoutManager(new LinearLayoutManager(getActivity()));
            ids = new ArrayList<>();
            strings = new ArrayList<>();

            systemRecAapter.setOnClickerList(new SystemRecAapter.OnClickerList() {
                @Override
                public void onClicker(int position) {
                    List<SystemDataBean.DataBean.ChildrenBean> children = dataBean.getData().get(position).getChildren();
                    for (int i = 0; i < children.size(); i++) {
                        int id = children.get(i).getId();
                        String name = children.get(i).getName();
                        ids.add(id);
                        strings.add(name);
                    }
                    Intent intent = new Intent(getContext(), SystemActivity.class);
                    intent.putExtra("ids",ids);
                    intent.putExtra("string",strings);
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public void setPresenter(SystemConstract.Systempresenter presenter) {
        mPresenter = presenter;
        mPresenter.getSystemListData();
        mPresenter.attachView(this);
    }

    @Override
    public Context getContentObject() {
        return null;
    }


}
