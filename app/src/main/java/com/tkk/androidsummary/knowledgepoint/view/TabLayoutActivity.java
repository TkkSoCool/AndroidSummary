package com.tkk.androidsummary.knowledgepoint.view;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.tkk.androidsummary.R;
import com.tkk.androidsummary.annotation.BindLayout;
import com.tkk.androidsummary.annotation.KnowledgeInfo;
import com.tkk.androidsummary.base.BaseActivity;
import com.tkk.androidsummary.knowledgepoint.frame.EventBus.TestFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created  on 2018/6/27 0027
 *
 * @author 唐开阔
 * @describe
 */
@BindLayout(R.layout.activity_tablayout)
@KnowledgeInfo(catalog = KnowledgeInfo.VIEW, desc = "TabLayout")
public class TabLayoutActivity  extends BaseActivity{
    String[] titles = {"待审批","已通过","未通过"};
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private List<Fragment> fragments = new ArrayList<>();
    @Override
    protected void initView() {
        for (int i = 0; i < titles.length; i++) {
            fragments.add(TestFragment.get(titles[i]));

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
                return titles[position];
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
}
