package com.example.wanandroid.system;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.wanandroid.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class SystemActivity extends AppCompatActivity {
    private static final String TAG = "SystemActivity";
    private TabLayout mTab;
    private ViewPager mVp;
    private ArrayList<Integer> ids;
    private ArrayList<String> string;
    private ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system);

        Intent intent = getIntent();
        ids = intent.getIntegerArrayListExtra("ids");
        string = intent.getStringArrayListExtra("string");
        Log.d(TAG, "onCreate11111111: " + ids.toString());
        Log.d(TAG, "onCreate222222222: " + string.toString());
        initView();

    }

    private void initView() {
        mTab = (TabLayout) findViewById(R.id.tab);
        mVp = (ViewPager) findViewById(R.id.vp);
        fragments = new ArrayList<>();
        for (int i = 0; i < string.size(); i++) {
            mTab.addTab(mTab.newTab().setText(string.get(i)));

        }
        for (int j = 0; j < ids.size(); j++) {
            fragments.add(new SystemListFragment(ids.get(j)));
        }
        MySystemFragment mySystemFragment = new MySystemFragment(getSupportFragmentManager(), fragments);
        mVp.setAdapter(mySystemFragment);
        mTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mVp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mVp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTab));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1,1,1,"分享");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
                intent.putExtra(Intent.EXTRA_TEXT, "分享文本。。。。");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(Intent.createChooser(intent, getTitle()));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
