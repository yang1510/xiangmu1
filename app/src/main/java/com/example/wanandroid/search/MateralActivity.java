package com.example.wanandroid.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wanandroid.R;
import com.example.wanandroid.home.WebActivity;
import com.example.wanandroid.search.bean.AlertionDataBaen;
import com.example.wanandroid.search.bean.SearchBean;
import com.example.wanandroid.search.mvp.MateralContracier;
import com.example.wanandroid.search.mvp.MateralPaserenter;
import com.example.wanandroid.search.mvp.MateralRlvAdapter;

import java.util.ArrayList;

public class MateralActivity extends AppCompatActivity implements MateralContracier.InavigationView, View.OnClickListener, MateralRlvAdapter.OnItemClickListener {

    private static final String TAG = MateralActivity.class.getName();
    private Toolbar mMteralToolbar;
    private RecyclerView mMateralRecycle;
    private String mMeart;
    private MateralContracier.Inavigationpaserenter mInavigationpaserenter;
    private ArrayList<AlertionDataBaen.DataBean.DatasBean> mDatasBeans;
    private MateralRlvAdapter mMateralRlvAdapter;
    /**
     * <-
     */
    private TextView mBack;

    /**
     * 发现跟多干活
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materal);
        initView();
        mMeart = getIntent().getStringExtra("meart");
        initData();
    }

    private void initData() {
        setPresenter(new MateralPaserenter());
        mInavigationpaserenter.getnavigation(mMeart);
    }

    private void initView() {
        mMteralToolbar = (Toolbar) findViewById(R.id.mteral_toolbar);
        mMteralToolbar.setTitle("WanAndroid");
        mMteralToolbar.setNavigationIcon(R.drawable.ic_action_navigation_arrow_back_inverted);
        setSupportActionBar(mMteralToolbar);
        mMteralToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mMateralRecycle = (RecyclerView) findViewById(R.id.materal_recycle);
        mMateralRecycle.setLayoutManager(new LinearLayoutManager(getContentObject()));
        mDatasBeans = new ArrayList<>();
        mMateralRlvAdapter = new MateralRlvAdapter(this, mDatasBeans);
        mMateralRecycle.setAdapter(mMateralRlvAdapter);
        mMateralRlvAdapter.setOnItemClickListener(this);
        mMateralRecycle.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mBack = (TextView) findViewById(R.id.back);
        mBack.setOnClickListener(this);
    }

    @Override
    public void OnSuccess(AlertionDataBaen navStricleBaen) {
        Log.d(TAG, "OnSuccess: ===========================navStricleBaen" + navStricleBaen);
        mMateralRlvAdapter.setDATA(navStricleBaen);

    }

    @Override
    public void OnFild(String str) {

    }

    @Override
    public void OnSouonSuccess(SearchBean souBaen) {

    }

    @Override
    public void OnSouFild(String str) {

    }

    @Override
    public void setPresenter(MateralContracier.Inavigationpaserenter presenter) {
        mInavigationpaserenter = presenter;
        mInavigationpaserenter.attachView(this);
    }

    @Override
    public Context getContentObject() {
        return null;
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(MateralActivity.this, WebActivity.class);
        intent.putExtra("web", mDatasBeans.get(position).getLink());
        startActivity(intent);
    }


    @Override
    public void onClick(View view) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
