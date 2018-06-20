package com.tkk.androidsummary.knowledgepoint.frame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tkk.androidsummary.R;
import com.tkk.androidsummary.annotation.BindLayout;
import com.tkk.androidsummary.annotation.KnowledgeInfo;
import com.tkk.androidsummary.base.BaseActivity;
import com.tkk.androidsummary.bean.ActivityData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created  on 2018/1/3
 *
 * @author 唐开阔
 * CollapsingToolbarLayout实现折叠效果
 */
@KnowledgeInfo(catalog = KnowledgeInfo.FRAME, desc = "CollapsingToolbarLayout使用")
@BindLayout(R.layout.activity_collapsing_toolbar_layout)
public class CollapsingToolbarLayoutActivity extends BaseActivity {
    List<String> datas = new ArrayList<>();


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void initView() {
        getSupportActionBar().hide();
        for (int i = 0; i < 50; i++) {
            datas.add(i + " ");
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new BaseQuickAdapter<String, BaseViewHolder>(R.layout.list_homepage_item, datas) {
            @Override
            protected void convert(BaseViewHolder helper, final String item) {
                helper.setText(R.id.tv_name, item);
            }
        });
    }


}
