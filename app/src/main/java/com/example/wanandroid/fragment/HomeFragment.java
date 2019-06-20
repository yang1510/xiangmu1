package com.example.wanandroid.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wanandroid.R;
import com.example.wanandroid.home.MyHomeAdapter;
import com.example.wanandroid.home.WebActivity;
import com.example.wanandroid.home.bean.BannerBean;
import com.example.wanandroid.home.bean.DaBean;
import com.example.wanandroid.home.bean.DatasBean;
import com.example.wanandroid.home.mvp.HomeContact;
import com.example.wanandroid.home.mvp.HomeModule;
import com.example.wanandroid.home.mvp.HomePresenter;
import com.example.wanandroid.official.MyOfficialListAdapter;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements HomeContact.IHomeView {


    public static RecyclerView mRl;
    private ArrayList<BannerBean.DataBean> banner = new ArrayList<>();
    private ArrayList<DatasBean> data = new ArrayList<>();
    private MyHomeAdapter myHomeAdapter;
    HomeContact.IHomePresenter presenter;
    private SmartRefreshLayout smar;
    private int paga=0;

    public HomeFragment() {
        // Required empty public constructor
    }

    private static final String TAG = "HomeFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_home, container, false);

        initData();
        initView(inflate);
        return inflate;
    }

    private void initData() {
        setPresenter(new HomePresenter());
        if (banner.size()==0){
            presenter.getBanner();
        }
        presenter.getData(paga);
    }

    private void initView(final View inflate) {
        mRl = inflate.findViewById(R.id.rl);
        smar = inflate.findViewById(R.id.smar);
        mRl.setLayoutManager(new LinearLayoutManager(getContext()));
        myHomeAdapter = new MyHomeAdapter(getContext(), banner, data);
        mRl.setAdapter(myHomeAdapter);

        //添加Android自带的分割线
        mRl.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        myHomeAdapter.setOnClickerList(new MyHomeAdapter.OnClickerList() {
            @Override
            public void onChlicker(int position) {
                Intent intent = new Intent(getContext(), WebActivity.class);
                String link = data.get(position).getLink();
                intent.putExtra("web", link);
                startActivity(intent);
            }
        });
        smar.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                banner.clear();
                data.clear();
                initData();
                smar.finishRefresh();
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                paga++;
                initData();
                smar.finishLoadMore();
            }
        });
        FloatingActionButton floating = getActivity().findViewById(R.id.floating);
        floating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             mRl.smoothScrollToPosition(0);
            }
        });
        myHomeAdapter.setClick(new MyOfficialListAdapter.OnClick() {
            @Override
            public void Click(View v, int i, boolean isChbox) {
                if (isChbox){
                    WADataService.getService().ShouCang(String.valueOf(data.get(i).getId()))
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

    @Override
    public void onDaYes(DaBean bean) {
        //data.addAll(bean.getData().getDatas());
        myHomeAdapter.add(bean);
    }

    @Override
    public void onBannerYes(BannerBean bannerBean) {
        banner.addAll(bannerBean.getData());
        myHomeAdapter.setBannerList(banner);
        myHomeAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDaNo(String msg) {
        Log.d(TAG, "onDaNo: " + msg);
    }

    @Override
    public void onBannerNo(String msg) {
        Log.d(TAG, "onBannerNo: " + msg);
    }

    @Override
    public void setPresenter(HomeContact.IHomePresenter presenter1) {
        presenter = presenter1;
        presenter.attachView(this);
    }

    @Override
    public Context getContentObject() {
        return null;
    }
}
