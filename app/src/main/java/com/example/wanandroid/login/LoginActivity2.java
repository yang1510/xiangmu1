package com.example.wanandroid.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wanandroid.MainActivity;
import com.example.wanandroid.R;
import com.google.android.material.textfield.TextInputLayout;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity2 extends AppCompatActivity implements View.OnClickListener {

    private FrameLayout mFl;
    private ImageView mImageView4;
    /**
     * 用户名
     */
    private EditText mLogEtName;
    /**
     * 密码
     */
    private EditText mLogEtPas;
    private TextInputLayout mPas;
    /**
     * 登陆
     */
    private Button mLogLogin;
    /**
     * 没有账号？去注册
     */
    private TextView mLogText;
    private LinearLayout mLog;
    private ImageView mRegistImg;
    /**
     * 用户名
     */
    private EditText mRegistName;
    /**
     * 密码
     */
    private EditText mRegistPas;
    private TextInputLayout mText1;
    /**
     * 在输入一遍密码
     */
    private EditText mRegistPas2;
    private TextInputLayout mText2;
    /**
     * 注册
     */
    private Button mRegistRegist;
    /**
     * 已有账户？去登陆
     */
    private TextView mRegistText;
    private LinearLayout mRegist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        initView();
    }

    private static final String TAG = "LoginActivity2";
    private void initView() {
        //登陆
        mImageView4 = (ImageView) findViewById(R.id.imageView4);
        mLogEtName = (EditText) findViewById(R.id.log_et_name);
        mLogEtPas = (EditText) findViewById(R.id.log_et_pas);
        mPas = (TextInputLayout) findViewById(R.id.pas);
        mLogLogin = (Button) findViewById(R.id.log_login);
        mLogLogin.setOnClickListener(this);
        mLogText = (TextView) findViewById(R.id.log_text);
        mLog = (LinearLayout) findViewById(R.id.log);
        //注册
        mRegistImg = (ImageView) findViewById(R.id.regist_img);
        mRegistName = (EditText) findViewById(R.id.regist_name);
        mRegistPas = (EditText) findViewById(R.id.regist_pas);
        mText1 = (TextInputLayout) findViewById(R.id.text1);
        mRegistPas2 = (EditText) findViewById(R.id.regist_pas2);
        mText2 = (TextInputLayout) findViewById(R.id.text2);
        mRegistRegist = (Button) findViewById(R.id.regist_regist);
        mRegistRegist.setOnClickListener(this);
        mRegistText = (TextView) findViewById(R.id.regist_text);
        mRegist = (LinearLayout) findViewById(R.id.regist);
        mLogText.setOnClickListener(this);
        mRegistText.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.log_login:
                login();
                break;
            case R.id.regist_regist:
                regist();
                break;
            case R.id.log_text:
                mLog.setVisibility(View.GONE);
                mRegist.setVisibility(View.VISIBLE);
                break;
            case R.id.regist_text:
                mLog.setVisibility(View.VISIBLE);
                mRegist.setVisibility(View.GONE);
                break;
        }
    }

    private void regist() {
        final String rigist_name = mRegistName.getText().toString().trim();
        String rigist_pas = mRegistPas.getText().toString().trim();
        String megist_pas2 = mRegistPas2.getText().toString().trim();
        Retrofit build = new Retrofit.Builder()
                .baseUrl(LoginApi.project)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final Observable<Register_Bean> register = build.create(LoginApi.class).getRegister(rigist_name, rigist_pas, megist_pas2);
        register.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Register_Bean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Register_Bean register_bean) {
                int errorCode = register_bean.getErrorCode();
                if (errorCode == 0) {
                    Log.d(TAG, "onNext: " + register_bean.getData().getUsername());
                    Toast.makeText(LoginActivity2.this, register_bean.getErrorMsg(), Toast.LENGTH_SHORT).show();
                    //EventBus.getDefault().postSticky(rigist_name);
                    mLogEtName.setText(mRegistName.getText().toString().trim());
                    mLogEtPas.setText(mRegistPas.getText().toString().trim());
                    mRegist.setVisibility(View.VISIBLE);
                    mLog.setVisibility(View.GONE);

                } else {
                    Toast.makeText(LoginActivity2.this, register_bean.getErrorMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onNext:shi " + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void login() {
        final String login_name = mLogEtName.getText().toString().trim();
        String login_paw = mLogEtPas.getText().toString().trim();
        Retrofit build = new Retrofit.Builder()
                .baseUrl(LoginApi.project)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Observable<Register_Bean> login = build.create(LoginApi.class).getLogin(login_name, login_paw);
        login.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Register_Bean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Register_Bean register_bean) {
                int errorCode = register_bean.getErrorCode();
                if (errorCode == 0) {
                    Log.d(TAG, "onNext: " + register_bean.getData().getUsername());
                    Toast.makeText(LoginActivity2.this,"登陆成功", Toast.LENGTH_SHORT).show();
                    SharedPreferences username_login = getSharedPreferences("username_login", MODE_PRIVATE);
                    SharedPreferences.Editor edit = username_login.edit();
                    edit.putString("myusername",register_bean.getData().getUsername());
                    edit.commit();
                    EventBus.getDefault().postSticky(login_name);
                    Intent intent = new Intent(LoginActivity2.this, MainActivity.class);
                    startActivity(intent);

                    finish();
                } else {
                    Toast.makeText(LoginActivity2.this, register_bean.getErrorMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onNext:shi " + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
