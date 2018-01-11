package com.tkk.androidsummary.knowledgepoint.frame.EventBus;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.tkk.androidsummary.R;
import com.tkk.androidsummary.annotation.BindLayout;
import com.tkk.androidsummary.annotation.KnowledgeInfo;
import com.tkk.androidsummary.base.BaseActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * Created  on 2018/1/3
 * @author 唐开阔
 * 主体流程：
 * 1：通过双重检查锁定模式获取EventBus单例对象
 * 2：注册register
    a):
 */
@KnowledgeInfo(catalog = KnowledgeInfo.FRAME, desc = "EventBus使用及源码分析")
@BindLayout(R.layout.activity_event_bus)
/**
 *
 */
public class EventBusActivity extends BaseActivity {
    ArrayList<Fragment> fragments = new ArrayList<>();
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @Override
    protected void initView() {
        fragments.add(TestFragment.get("fragment1"));
        fragments.add(TestFragment.get("fragment2"));
        fragments.add(TestFragment.get("fragment3"));
        viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return 3;
            }
        });
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEvent(MessageEvent messageEvent) {
        Log.d(TAG, ">>>onMoonEvent---" + messageEvent.getMessage());
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEvent2(MessageEvent messageEvent) {
        Log.d(TAG, ">>>onMoonEvent2---" + messageEvent.getMessage());
    }
}
