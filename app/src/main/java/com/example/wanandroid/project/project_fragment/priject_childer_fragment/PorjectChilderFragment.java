package com.example.wanandroid.project.project_fragment.priject_childer_fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wanandroid.R;
import com.example.wanandroid.base.BaseFragment;
import com.example.wanandroid.home.WebActivity;
import com.example.wanandroid.home.bean.DatasBean;
import com.example.wanandroid.project.bean.project_bean.project_chlider_bean.Project_list_bean;
import com.example.wanandroid.project.project_adapter.Project_Adapter;
import com.example.wanandroid.shoucang.login.okhttp.WADataService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class PorjectChilderFragment extends BaseFragment implements Project_chlider_connector.Project_childer_view {
    private RecyclerView mPrjectChliderRecy;
    private SmartRefreshLayout mPrjectChliderSmart;
    private Project_chlider_connector.Project_childer_present presnet1;
    private int page1 = 1;
    private int cid;
    private Project_Adapter project_adapter;
    private ArrayList<DatasBean> dataBean_chliders;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View inflate = View.inflate(getContext(), R.layout.fragment_project_childer, null);
        initView(inflate);
        Bundle arguments = getArguments();
        cid = arguments.getInt("cid");
        setPresenter(new Project_chlider_present());
        initclick();
        return inflate;
    }


    private void initView(View inflate) {
        mPrjectChliderRecy = (RecyclerView) inflate.findViewById(R.id.prject_chlider_recy);
        mPrjectChliderSmart = (SmartRefreshLayout) inflate.findViewById(R.id.prject_chlider_smart);
        dataBean_chliders = new ArrayList<>();
        mPrjectChliderRecy.setLayoutManager(new LinearLayoutManager(getContext()));
        project_adapter = new Project_Adapter(getContext(), dataBean_chliders);
        mPrjectChliderRecy.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        mPrjectChliderRecy.setAdapter(project_adapter);
        FloatingActionButton floating = getActivity().findViewById(R.id.floating);
        floating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPrjectChliderRecy.smoothScrollToPosition(0);
            }
        });
        project_adapter.setClick(new Project_Adapter.OnClick() {
            @Override
            public void Click(View v, int i, boolean isChbox) {
                if (isChbox){
                    WADataService.getService().ShouCang(String.valueOf(dataBean_chliders.get(i).getId()))
                            .subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<ResponseBody>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(ResponseBody responseBody) {
                                    Toast.makeText(getContext(), "收藏成功", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onError(Throwable e) {
                                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                    Log.d(TAG, "onError: "+e.getMessage());
                                }

                                @Override
                                public void onComplete() {

                                }
                            });
                }
            }
        });
    }

    private void initclick() {
        mPrjectChliderSmart.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page1++;
                presnet1.Project_Present_childer_data();
                refreshLayout.finishLoadMore();
            }
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page1 = 1;
                presnet1.Project_Present_childer_data();
                refreshLayout.finishRefresh();

            }
        });


        project_adapter.ProjectAdapter_Setonclick(new Project_Adapter.setonclick() {
            @Override
            public void setOnclick(View v, int position) {
                String link = dataBean_chliders.get(position).getLink();
             Intent intent = new Intent(getContext(), WebActivity.class);
                intent.putExtra("web",link);
                startActivity(intent);
            }
        });
    }

    @Override
    public void Onsuccessful(Project_list_bean bean_childer) {
        project_adapter.getData(bean_childer);
    }

    @Override
    public void OnFailder(String msg) {

    }

    @Override
    public int getpage() {
        return page1;
    }

    @Override
    public int getcid() {
        return cid;
    }


    @Override
    public void setPresenter(Project_chlider_connector.Project_childer_present presenter) {
        presnet1 = presenter;
        presnet1.attachView(this);
        presnet1.Project_Present_childer_data();
    }

    @Override
    public Context getContentObject() {
        return null;
    }


}
