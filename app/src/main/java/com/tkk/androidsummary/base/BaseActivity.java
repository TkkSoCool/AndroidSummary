package com.tkk.androidsummary.base;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.beloo.widget.chipslayoutmanager.util.log.IFillLogger;
import com.tkk.androidsummary.annotation.BindLayout;
import com.tkk.androidsummary.annotation.KnowledgeInfo;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by tkk on 2017/7/5.
 */

public abstract  class BaseActivity extends RxAppCompatActivity implements LifecycleOwner {
    protected final String TAG = this.getClass().getSimpleName();
    public Unbinder unbinder;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewPre();
        KnowledgeInfo knowledgeInfo = getClass().getAnnotation(KnowledgeInfo.class);
        setContentView(getClass().getAnnotation(BindLayout.class).value());
        unbinder = ButterKnife.bind(this);
        ActionBar actionBar =  getSupportActionBar();
        if (knowledgeInfo!=null && actionBar != null){
            actionBar.setTitle(knowledgeInfo.desc());
        }
        initView();

    }
    protected  void initViewPre(){};

    protected  abstract void initView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }



}
