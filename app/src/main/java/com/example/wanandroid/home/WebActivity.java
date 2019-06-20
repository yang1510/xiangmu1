package com.example.wanandroid.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.wanandroid.R;

public class WebActivity extends AppCompatActivity {

    private WebView mWeb;
    private Toolbar mToolbar;
    private String mWeb1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        initView();
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mWeb = (WebView) findViewById(R.id.web);
        Intent intent = getIntent();
        mWeb1 = intent.getStringExtra("web");
        mWeb.setWebViewClient(new WebViewClient());
        mWeb.getSettings().setJavaScriptEnabled(true);
        mWeb.loadUrl(mWeb1);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(4, 4, 4, "分享");
        menu.add(2, 2, 2, "收藏");
        menu.add(3, 3, 3, "用浏览器打开");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 4:
                share();
                break;
            case 2:
                break;
            case 3:
                liulanqi();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void liulanqi() {
        final Uri uri = Uri.parse(mWeb1);
        final Intent it = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(it);
    }

    private void share() {
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
        intent.putExtra(Intent.EXTRA_TEXT, "分享文本。。。。");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(Intent.createChooser(intent, getTitle()));

    }
}
