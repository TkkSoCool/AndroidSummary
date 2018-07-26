package com.tkk.androidsummary.knowledgepoint.view.fragment_life;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;

import com.tkk.androidsummary.R;
import com.tkk.androidsummary.annotation.BindLayout;
import com.tkk.androidsummary.annotation.KnowledgeInfo;
import com.tkk.androidsummary.base.BaseActivity;
import com.tkk.androidsummary.knowledgepoint.frame.EventBus.TestFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@BindLayout(R.layout.activity_fragment_left)
@KnowledgeInfo(catalog = KnowledgeInfo.VIEW, desc = "Fragment生命周期")
public class FragmentLifeActivity extends BaseActivity {
    @BindView(R.id.fragment_content)
    FrameLayout fragmentContent;
    private List<String> titles = new ArrayList<>();
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private List<Fragment> fragments = new ArrayList<>();
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private LifeFragment lifeFragment1, lifeFragment2;

    @Override
    protected void initView() {
        manager = getSupportFragmentManager();
        lifeFragment1 = LifeFragment.newInstance("lifeFragment1");
        lifeFragment2 = LifeFragment.newInstance("lifeFragment2");
        for (int i = 0; i < 5; i++) {
            fragments.add(TestFragment.get("标题" + i));
            titles.add("tab" + i);
        }
        viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titles.get(position);
            }
        });
        tablayout.setupWithViewPager(viewpager);
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


    @OnClick({R.id.add1, R.id.hide1, R.id.show1, R.id.add2, R.id.replace2, R.id.remove_all,R.id.detach1, R.id.attach1})
    public void onViewClicked(View view) {
        transaction = manager.beginTransaction();
        switch (view.getId()) {
            case R.id.add1:
                transaction.add(R.id.fragment_content, lifeFragment1, "lifeFragment1");
                break;
            case R.id.hide1:
                transaction.hide(lifeFragment1);
                break;
            case R.id.show1:
                transaction.show(lifeFragment1);
            case R.id.detach1:
                transaction.detach(lifeFragment1);
                break;
            case R.id.attach1:
                transaction.attach(lifeFragment1);
                break;
            case R.id.add2:
                transaction.add(R.id.fragment_content, lifeFragment2, "lifeFragment2");

                break;
            case R.id.replace2:
                transaction.replace(R.id.fragment_content, lifeFragment2);
                break;
            case R.id.remove_all:
                transaction.remove(lifeFragment1);
                break;
        }
        transaction.commit();
    }


}
