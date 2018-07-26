package com.tkk.androidsummary;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.beloo.widget.chipslayoutmanager.ChipsLayoutManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tkk.androidsummary.annotation.BindLayout;
import com.tkk.androidsummary.annotation.KnowledgeInfo;
import com.tkk.androidsummary.base.BaseActivity;
import com.tkk.androidsummary.bean.ActivityData;
import com.tkk.androidsummary.view.MarginItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@BindLayout(R.layout.activity_main)
public class MainActivity extends BaseActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    List<ActivityData> datas = new ArrayList<>();
    @Override
    protected void initView() {
        recyclerView.setLayoutManager(ChipsLayoutManager.newBuilder(this).build());
        recyclerView.addItemDecoration(new MarginItemDecoration(20));
        initActivityInfos();
        recyclerView.setAdapter(new BaseQuickAdapter<ActivityData,BaseViewHolder>(R.layout.list_homepage_item,datas) {
            @Override
            protected void convert(BaseViewHolder helper, final ActivityData item) {
                helper.setText(R.id.tv_name,item.getDesc());
                helper.getView(R.id.tv_name).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setComponent(item.getComponentName());
                        startActivity(intent);

                    }
                });
            }
        });
    }

    private void initActivityInfos() {
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getApplication().getPackageName(), PackageManager.GET_ACTIVITIES);
            ActivityInfo[] activities = packageInfo.activities;
            /**
             * 轮询所有activity的KnowledgeInfo注解信息
             */
            for (ActivityInfo ac : activities) {
                try {
                    KnowledgeInfo knowledgeInfo = Class.forName(ac.name).getAnnotation(KnowledgeInfo.class);
                    if (knowledgeInfo != null && knowledgeInfo.isShow()) {
                        ComponentName componentName = new ComponentName(getApplication().getPackageName(),ac.name);
                        datas.add(new ActivityData(componentName, knowledgeInfo.catalog(),knowledgeInfo.desc()));
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }


}
