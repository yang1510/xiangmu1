package com.example.wanandroid.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.wanandroid.R;
import com.example.wanandroid.search.bean.SearchBean;
import com.example.wanandroid.util.FlowLayout;
import com.example.wanandroid.widget.UIUtils;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity   {

    private Toolbar mToolbar;
    private MaterialSearchView mSearchView;
    private SearchAdapter searchAdapter;
    private ArrayList<SearchBean.DataBean> dataBeans;
    private FlowLayout mFl;
    /**
     * 《
     */
    private TextView mBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        initData();
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SearchApi.searchUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        final SearchApi searchApi = retrofit.create(SearchApi.class);
        final Observable<SearchBean> search = searchApi.getSearch();
        search.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SearchBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SearchBean searchBean) {
                        dataBeans.addAll(searchBean.getData());
//                        searchAdapter.setList(dataBeans);
//                        searchAdapter.notifyDataSetChanged();
                        mFl.removeAllViews();
                        for (int i = 0; i < dataBeans.size(); i++) {
                            //获取视图，视图可以自定义，可以添加自己想要的效果
                            View inflate = (View) View.inflate(SearchActivity.this, R.layout.layout_navi_item, null);
                            TextView tv_title = inflate.findViewById(R.id.tv_title);
                            final String name = dataBeans.get(i).getName();
                            tv_title.setText(name);
                            setTextColor(name, tv_title, i);
                            mFl.addView(inflate);
                            tv_title.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(SearchActivity.this, MateralActivity.class);
                                    intent.putExtra("meart", name);
                                    startActivity(intent);
                                }
                            });
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void setTextColor(String title, TextView label, int i) {
        String s = label.getText().toString();
        if (title.length() > 0 && title.length() < 3) {
            label.setTextColor(UIUtils.getColor(R.color.Purple));
        } else if (title.length() >= 3 && title.length() < 6) {
            label.setTextColor(UIUtils.getColor(R.color.colorPrimaryDark));
        } else if (title.length() >= 6 && title.length() < 9) {
            label.setTextColor(UIUtils.getColor(R.color.Red));
        } else if (title.length() >= 9 && title.length() < 12) {
            label.setTextColor(UIUtils.getColor(R.color.Blue));
        } else if (title.length() >= 12) {
            label.setTextColor(UIUtils.getColor(R.color.Green));
        }
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_action_navigation_arrow_back_inverted);
        mSearchView = (MaterialSearchView) findViewById(R.id.search_view);
        mFl = (FlowLayout) findViewById(R.id.fl);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dataBeans = new ArrayList<>();
        // searchAdapter = new SearchAdapter(dataBeans, this);
        mSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
//搜索框按下时的回调
                Intent intent = new Intent(SearchActivity.this, MateralActivity.class);
                intent.putExtra("meart", query);
                startActivity(intent);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //搜索框内容发生改变是的回调
                return false;
            }
        });
        mSearchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //显示搜索框
            }

            @Override
            public void onSearchViewClosed() {
                //隐藏搜索框

            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu3, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        mSearchView.setMenuItem(item);
        //mToolbar.setVisibility(View.GONE);
        return true;
    }
}
