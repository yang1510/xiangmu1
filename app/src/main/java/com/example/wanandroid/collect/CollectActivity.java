package com.example.wanandroid.collect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wanandroid.R;
import com.example.wanandroid.home.WebActivity;
import com.example.wanandroid.official.MyOfficialListAdapter;
import com.example.wanandroid.shoucang.login.okhttp.WADataService;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class CollectActivity extends AppCompatActivity {

    private RecyclerView mRl;
    private ArrayList<CollectBean.DataBean.DatasBean> datasBeans;
    private MyCollectAdapter myCollectAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        initView();
        initData();

    }

    private void initData() {
//        DaoSession daoSession = BaseApp.getDaoSession();
//        List<DatasBean> list = daoSession.loadAll(DatasBean.class);
//        datasBeans.addAll(list);
//        myCollectAdapter.setList(datasBeans);
//        myCollectAdapter.notifyDataSetChanged();
        WADataService.getService().ZhanShi().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<CollectBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(CollectBean collectBean) {
                datasBeans.addAll(collectBean.getData().getDatas());
                myCollectAdapter.setList(datasBeans);
                myCollectAdapter.notifyDataSetChanged();
                int size = collectBean.getData().getDatas().size();
                Toast.makeText(CollectActivity.this, size + "", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(CollectActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void initView() {
        mRl = (RecyclerView) findViewById(R.id.rl);
        mRl.setLayoutManager(new LinearLayoutManager(this));
        datasBeans = new ArrayList<>();
        myCollectAdapter = new MyCollectAdapter(this, datasBeans);
        mRl.setAdapter(myCollectAdapter);
        myCollectAdapter.setOnItemClickListener(new MyCollectAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(CollectActivity.this, WebActivity.class);
                intent.putExtra("web", datasBeans.get(position).getLink());
                startActivity(intent);
            }
        });
        //添加Android自带的分割线
        mRl.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        myCollectAdapter.setClick(new MyOfficialListAdapter.OnClick() {
            @Override
            public void Click(View v, final int i, boolean isChbox) {
                WADataService.getService().quxiao(datasBeans.get(i).getId(), datasBeans.get(i).getOriginId())
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<ResponseBody>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(ResponseBody responseBody) {

                                datasBeans.remove(i);
                                myCollectAdapter.notifyDataSetChanged();
                                Toast.makeText(CollectActivity.this, "取消成功", Toast.LENGTH_LONG).show();

                            }

                            @Override
                            public void onError(Throwable e) {
                                Toast.makeText(CollectActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        });

    }
}
