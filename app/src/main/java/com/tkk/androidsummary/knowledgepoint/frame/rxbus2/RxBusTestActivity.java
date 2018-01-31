package com.tkk.androidsummary.knowledgepoint.frame.rxbus2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.hwangjr.rxbus.thread.EventThread;
import com.tkk.androidsummary.R;
import com.tkk.androidsummary.annotation.BindLayout;
import com.tkk.androidsummary.annotation.KnowledgeInfo;
import com.tkk.androidsummary.base.BaseActivity;

import java.util.Date;
import java.util.List;

@KnowledgeInfo(catalog = KnowledgeInfo.FRAME, desc = "RxBus2")
@BindLayout(R.layout.activity_rx_bus_test)
public class RxBusTestActivity extends BaseActivity {
    @Override
    protected void initView() {
        long l1 =  System.currentTimeMillis();
        RxBus.get().register(this);
        long l2 =  System.currentTimeMillis();
        Log.d(TAG, ">>>RxBus注册耗时---" + (l2-l1));
        long l3 =  System.currentTimeMillis();
        RxBus.get().post("0",new Object());
        RxBus.get().post("0",new Object());
        RxBus.get().post("0",new Object());
        RxBus.get().post("0",new Object());
        RxBus.get().post("0",new Object());
        RxBus.get().post("0",new Object());
        long l4 =  System.currentTimeMillis();

        Log.d(TAG, ">>>RxBus发送耗时---" + (l4-l3));
    }
    @Subscribe(thread = EventThread.IO, tags = {@Tag("0")})
    public void metood0(Object foods) {
    }
    @Subscribe(thread = EventThread.IO, tags = {@Tag("0")})
    public void metood1(Object foods) {
    }
    @Subscribe(thread = EventThread.IO, tags = {@Tag("0")})
    public void metood2(Object foods) {
    }
    @Subscribe(thread = EventThread.IO, tags = {@Tag("0")})
    public void metood3(Object foods) {
    }
    @Subscribe(thread = EventThread.IO, tags = {@Tag("0")})
    public void metood4(Object foods) {
    }
    @Subscribe(thread = EventThread.IO, tags = {@Tag("0")})
    public void metood5(Object foods) {
    }
    @Subscribe(thread = EventThread.IO, tags = {@Tag("0")})
    public void metood6(Object foods) {
    }
    @Subscribe(thread = EventThread.IO, tags = {@Tag("0")})
    public void metood7(Object foods) {
    }
    @Subscribe(thread = EventThread.IO, tags = {@Tag("0")})
    public void metood8(Object foods) {
    }
    @Subscribe(thread = EventThread.IO, tags = {@Tag("0")})
    public void metood9(Object foods) {
    }
    @Subscribe(thread = EventThread.IO, tags = {@Tag("0")})
    public void metood10(Object foods) {
    }
    @Subscribe(thread = EventThread.IO, tags = {@Tag("0")})
    public void metood11(Object foods) {
    }
    @Subscribe(thread = EventThread.IO, tags = {@Tag("0")})
    public void metood12(Object foods) {
    }
    @Subscribe(thread = EventThread.IO, tags = {@Tag("0")})
    public void metood13(Object foods) {
    }
    @Subscribe(thread = EventThread.IO, tags = {@Tag("0")})
    public void metood14(Object foods) {
    }
    @Subscribe(thread = EventThread.IO, tags = {@Tag("0")})
    public void metood16(Object foods) {
    }
    @Subscribe(thread = EventThread.IO, tags = {@Tag("0")})
    public void metood17(Object foods) {
    }    @Subscribe(thread = EventThread.IO, tags = {@Tag("0")})
    public void metood18(Object foods) {
    }    @Subscribe(thread = EventThread.IO, tags = {@Tag("0")})
    public void metood19(Object foods) {
    }    @Subscribe(thread = EventThread.IO, tags = {@Tag("0")})
    public void metood20(Object foods) {
    }    @Subscribe(thread = EventThread.IO, tags = {@Tag("0")})
    public void metood15(Object foods) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);

    }
}
