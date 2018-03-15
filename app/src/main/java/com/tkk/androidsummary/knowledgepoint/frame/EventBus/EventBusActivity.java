package com.tkk.androidsummary.knowledgepoint.frame.EventBus;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Tag;
import com.hwangjr.rxbus.thread.EventThread;
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
        long l1 =  System.currentTimeMillis();
        EventBus.getDefault().register(this);
        long l2 =  System.currentTimeMillis();
        Log.d(TAG, ">>>EventBus注册耗时---" + (l2-l1));
        long l3 =  System.currentTimeMillis();
        EventBus.getDefault().post(new Object());
        EventBus.getDefault().post(new Object());
        EventBus.getDefault().post(new Object());
        EventBus.getDefault().post(new Object());
        EventBus.getDefault().post(new Object());
        EventBus.getDefault().post(new Object());
        long l4 =  System.currentTimeMillis();

        Log.d(TAG, ">>>EventBus发送耗时---" + (l4-l3));

    }
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void metood0(Object foods) {
    }
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void metood1(Object foods) {
    }
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void metood2(Object foods) {
    }
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void metood3(Object foods) {
    }
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void metood4(Object foods) {
    }
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void metood5(Object foods) {
    }
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void metood6(Object foods) {
    }
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void metood7(Object foods) {
    }
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void metood8(Object foods) {
    }
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void metood9(Object foods) {
    }
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void metood10(Object foods) {
    }
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void metood11(Object foods) {
    }
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void metood12(Object foods) {
    }
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void metood13(Object foods) {
    }
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void metood14(Object foods) {
    }
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void metood16(Object foods) {
    }
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void metood17(Object foods) {
    }
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void metood18(Object foods) {
    }
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void metood19(Object foods) {
    }
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void metood20(Object foods) {
    }
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void metood15(Object foods) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }
}
