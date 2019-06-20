package com.example.wanandroid.official;


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
import com.example.wanandroid.home.WebActivity;
import com.example.wanandroid.home.bean.DatasBean;
import com.example.wanandroid.official.api.OfficialApi;
import com.example.wanandroid.official.bean.DataListBean;
import com.example.wanandroid.shoucang.login.okhttp.WADataService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class OfficialListFragment extends Fragment {
    //https://wanandroid.com/wxarticle/list/408/1/json

    public String url = "https://wanandroid.com/";

    private RecyclerView rl;
    private ArrayList<DatasBean> datasBeans;
    private MyOfficialListAdapter myOfficialListAdapter;
    private int ss;
    private SmartRefreshLayout smar;

    public OfficialListFragment(int s) {
        Log.d(TAG, "OfficialListFragmentoooooooooooooooooooo: " + s);
        ss = s;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_official_list, container, false);
        initView(inflate);
        initData();
        return inflate;
    }

    private static final String TAG = "OfficialListFragment";

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(OfficialApi.url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        OfficialApi officialApi = retrofit.create(OfficialApi.class);
        Observable<DataListBean> data = officialApi.getData(ss);
        data.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DataListBean dataListBean) {
                        Log.d(TAG, "onNext11111111111111: " + dataListBean.getData().getDatas().size());
                        if (dataListBean != null) {
                            datasBeans.addAll(dataListBean.getData().getDatas());
                            myOfficialListAdapter.setList(datasBeans);
                            myOfficialListAdapter.notifyDataSetChanged();
                        }
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
        FloatingActionButton floating = getActivity().findViewById(R.id.floating);
        floating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rl.smoothScrollToPosition(0);
            }
        });
        smar = inflate.findViewById(R.id.smar);
        rl.setLayoutManager(new LinearLayoutManager(getContext()));
        datasBeans = new ArrayList<>();
        myOfficialListAdapter = new MyOfficialListAdapter(getContext(), datasBeans);
        rl.setAdapter(myOfficialListAdapter);
        //添加Android自带的分割线
        rl.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        myOfficialListAdapter.setOnClickerList(new MyOfficialListAdapter.onClickerList() {
            @Override
            public void onClicker(int position) {
                Intent intent = new Intent(getContext(), WebActivity.class);
                intent.putExtra("web", datasBeans.get(position).getLink());
                startActivity(intent);
            }
        });


        //-----------------------------
        myOfficialListAdapter.setClick(new MyOfficialListAdapter.OnClick() {
            @Override
            public void Click(View v, int i, boolean isChbox) {
                if (isChbox){
                    WADataService.getService().ShouCang(String.valueOf(datasBeans.get(i).getId()))
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


        //-----------------------------




        smar.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                ++ss;
                initData();
                smar.finishLoadMore();

            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                    datasBeans.clear();
                    initData();
                    smar.finishRefresh();
            }
        });

    }

}
