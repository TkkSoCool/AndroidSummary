package com.tkk.androidsummary.knowledgepoint.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tkk.androidsummary.R;
import com.tkk.androidsummary.annotation.BindLayout;
import com.tkk.androidsummary.annotation.KnowledgeInfo;
import com.tkk.androidsummary.base.BaseActivity;
import com.tkk.androidsummary.knowledgepoint.frame.EventBus.TestFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created  on 2018/1/16
 *
 * @author 唐开阔
 * @describe ViewPager和其适配器源码解析，如何实现懒加载
 */
@BindLayout(R.layout.activity_viewpager)
@KnowledgeInfo(catalog = KnowledgeInfo.VIEW, desc = "ViewPager Adapter")
public class ViewPagerAndAdapterActivity extends BaseActivity {
    @BindView(R.id.viewpager1)
    ViewPager viewpager1;
    @BindView(R.id.viewpager2)
    ViewPager viewpager2;
    private List<View> viewList = new ArrayList<>();
    ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    protected void initView() {
        for (int i = 0; i < 3; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.mipmap.img);
            fragments.add(TestFragment.get("fragment"+i));
            viewList.add(imageView);
        }
        viewpager1.setAdapter(new ImgPagerAdapter(viewList));
        viewpager2.setAdapter(new FPagerAdapter(getSupportFragmentManager(),fragments));

        PagerAdapter a;
        FragmentPagerAdapter b;
        FragmentStatePagerAdapter c;
    }
    public class FPagerAdapter extends FragmentPagerAdapter{
        ArrayList<Fragment> fragments = new ArrayList<>();

        public FPagerAdapter(FragmentManager fm,ArrayList<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments == null?0:fragments.size();
        }
    }
    public class ImgPagerAdapter extends PagerAdapter{
        private List<View> viewList;

        public ImgPagerAdapter(List<View> viewList) {
            this.viewList = viewList;
        }

        /**
         * @return 返回页面的个数
         */
        @Override
        public int getCount() {
            return viewList != null ? viewList.size() : 0;
        }

        /**
         * 判断对象是否生成界面
         * @param view
         * @param object
         * @return
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        /**
         * 初始化position位置的界面
         * @param container
         * @param position
         * @return
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewList.get(position));
            return viewList.get(position);
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(viewList.get(position));
        }
    }

}
