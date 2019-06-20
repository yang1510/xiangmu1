package com.example.wanandroid.system;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wanandroid.R;
import com.example.wanandroid.home.WebActivity;
import com.example.wanandroid.system.adapter.SystemCliledAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class SystemListFragment extends Fragment {


    private int paga;
    private RecyclerView rl;
    private SystemCliledAdapter systemCliledAdapter;
    private ArrayList<System_Clild.DataBean.DatasBean> datasBeans;
    private SmartRefreshLayout smart;

    public SystemListFragment(Integer integer) {
        // Required empty public constructor
        paga = integer;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_system_list, container, false);
        initView(inflate);
        initData();
        return inflate;
    }

    private static final String TAG = "SystemListFragment";

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Observable<System_Clild> clild = apiService.getClild(paga);
        clild.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<System_Clild>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(System_Clild system_clild) {
                        datasBeans.addAll(system_clild.getData().getDatas());
                        systemCliledAdapter.setList(datasBeans);
                        systemCliledAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initView(final View inflate) {
        rl = inflate.findViewById(R.id.rl);
        smart = inflate.findViewById(R.id.smart);
        rl.setLayoutManager(new LinearLayoutManager(getContext()));
        datasBeans = new ArrayList<>();
        systemCliledAdapter = new SystemCliledAdapter(getContext(), datasBeans);
        rl.setAdapter(systemCliledAdapter);
        //添加Android自带的分割线
        rl.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        systemCliledAdapter.setOnClickerList(new SystemCliledAdapter.OnClickerList() {
            @Override
            public void onClicker(int position) {
                Intent intent = new Intent(getContext(), WebActivity.class);
                intent.putExtra("web", datasBeans.get(position).getLink());
                startActivity(intent);
            }
        });
        smart.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                ++paga;
                initData();
                smart.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                datasBeans.clear();
                initData();
                smart.finishRefresh();
            }
        });

    }
}
