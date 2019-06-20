package com.example.wanandroid;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.wanandroid.collect.CollectActivity;
import com.example.wanandroid.fragment.HomeFragment;
import com.example.wanandroid.fragment.NaviFragment;
import com.example.wanandroid.fragment.OfficialFragment;
import com.example.wanandroid.fragment.PrecjetFragment;
import com.example.wanandroid.fragment.SystemFragment;
import com.example.wanandroid.home.api.Home_Api;
import com.example.wanandroid.search.SearchActivity;
import com.example.wanandroid.setting.Setting;
import com.example.wanandroid.shoucang.LoginRegisterActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    /**
     * 玩Android
     */
    private TextView mToolbarTitle;
    private Toolbar mToolbar;
    private NavigationView mNv;
    private DrawerLayout mDl;

    private TabLayout mTab;
    private ArrayList<Fragment> fragments;
    private FrameLayout mFl;
    private final int HOME = 0;
    private final int KNOW = 1;
    private final int OFFICIAL = 2;
    private final int NAVI = 3;
    private final int PROJECT = 4;
    private int listType = 0;
    private FragmentManager manager;
    private BottomNavigationView mBottomView;
    private TextView hand_name;
    private FloatingActionButton mFloating;

    private MenuItem item;
    private int mPosition1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        // initTob();
        initToolBar();
        EventBus.getDefault().register(this);

    }

    private void initTob() {
        mTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mPosition1 = tab.getPosition();

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEnve(String banner) {
        hand_name.setText(banner);
    }

    private void initToolBar() {
        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDl, mToolbar, 0, 0);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.what));
        mDl.addDrawerListener(toggle);
        toggle.syncState();
        mNv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                //    Toast.makeText(MainActivity.this,menuItem.getTitle(),Toast.LENGTH_LONG).show();
                switch (menuItem.getItemId()) {
                    case R.id.collect:
                        startActivity(new Intent(MainActivity.this, CollectActivity.class));
                        break;
                    case R.id.todo:
                        break;
                    case R.id.night:
                        break;
                    case R.id.setting:
                        startActivity(new Intent(MainActivity.this, Setting.class));
                        break;
                    case R.id.about:
                        break;
                    case R.id.out:
                        out();
                        break;
                }
                mDl.closeDrawer(Gravity.LEFT);
                return false;
            }
        });


        View headerView = mNv.getHeaderView(0);
        item = mNv.getMenu().findItem(R.id.out);

        hand_name = headerView.findViewById(R.id.hand_name);
        SharedPreferences username_login = getSharedPreferences("username_login", MODE_PRIVATE);
        String myusername = username_login.getString("myusername", null);
        if (myusername != null) {
            hand_name.setText(myusername);
            item.setVisible(true);
        } else {
            hand_name.setText("登陆");
        }
        hand_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginRegisterActivity.class);
                startActivity(intent);
            }
        });


    }

    private void out() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Home_Api.outUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        Home_Api home_api = retrofit.create(Home_Api.class);
        Observable<Out> out = home_api.getOut();
        out.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Out>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Out out) {
                        if (out.getErrorCode() == 0) {
                            hand_name.setText("登陆");
                            item.setVisible(false);

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

    private void initView() {
        // mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mNv = (NavigationView) findViewById(R.id.nv);
        mDl = (DrawerLayout) findViewById(R.id.dl);
        mTab = (TabLayout) findViewById(R.id.tab);

        mFl = (FrameLayout) findViewById(R.id.fl);
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        mBottomView = (BottomNavigationView) findViewById(R.id.bottomView);

        manager = getSupportFragmentManager();
        mNv.setVisibility(View.INVISIBLE);


        HomeFragment homeFragment = new HomeFragment();//首页
        fragments = new ArrayList<>();
        fragments.add(homeFragment);
        fragments.add(new SystemFragment());
        fragments.add(new OfficialFragment());
        fragments.add(new NaviFragment());
        fragments.add(new PrecjetFragment());
        mDl.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                //滑动过程中不断回调 slideOffset:0~1
                View content = mDl.getChildAt(0);
                View menu = drawerView;
                float scale = 1 - slideOffset;//1~0
                content.setTranslationX(menu.getMeasuredWidth() * (1 - scale));//0~width
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        showFragment(HOME);
        mBottomView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        showFragment(HOME);
                        mToolbarTitle.setText("玩Android");
                        break;
                    case R.id.action_knowledge_system:
                        showFragment(KNOW);
                        mToolbarTitle.setText("知识体系");
                        break;
                    case R.id.action_wechat:
                        showFragment(OFFICIAL);
                        mToolbarTitle.setText("公众号");
                        break;
                    case R.id.action_navigation:
                        showFragment(NAVI);
                        mToolbarTitle.setText("导航");
                        break;
                    case R.id.action_project:
                        showFragment(PROJECT);
                        mToolbarTitle.setText("项目");
                        break;
                }

                return true;
            }
        });

        mFloating = (FloatingActionButton) findViewById(R.id.floating);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu3, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //显示隐藏fragment
    private void showFragment(int type) {
        Fragment fragment2 = fragments.get(0);
        Fragment fragment = fragments.get(type);
        Fragment fragment1 = fragments.get(listType);
        FragmentTransaction transaction = manager.beginTransaction();
        if (!fragment.isAdded()) {
            transaction.add(R.id.fl, fragment);
        }
        transaction.hide(fragment1);
        transaction.show(fragment);
        transaction.commit();
        listType = type;

    }


}
