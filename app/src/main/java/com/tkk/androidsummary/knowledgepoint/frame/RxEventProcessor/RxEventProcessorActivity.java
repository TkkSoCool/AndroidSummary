package com.tkk.androidsummary.knowledgepoint.frame.RxEventProcessor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.tkk.androidsummary.R;
import com.tkk.androidsummary.annotation.BindLayout;
import com.tkk.androidsummary.annotation.KnowledgeInfo;
import com.tkk.androidsummary.base.BaseActivity;
import com.tkk.api.RxEventProcessor;
import com.tkk.api.ThreadType;
import com.tkk.api.annotation.Recipient;

@KnowledgeInfo(catalog = KnowledgeInfo.FRAME, desc = "RxEventProcessor")
@BindLayout(R.layout.activity_rx_event_processor)
public class RxEventProcessorActivity extends BaseActivity {


    @Override
    protected void initView() {
        long l1 =  System.currentTimeMillis();
        RxEventProcessor.get().bind(this);
        long l2 =  System.currentTimeMillis();
        Log.d("tag", ">>>RxEventProcessor注册耗时---" + (l2-l1));
        long l3 =  System.currentTimeMillis();
        RxEventProcessor.get().post("metood1",new Object());
        RxEventProcessor.get().post("metood1",new Object());
        RxEventProcessor.get().post("metood1",new Object());
        RxEventProcessor.get().post("metood1",new Object());
        RxEventProcessor.get().post("metood1",new Object());
        RxEventProcessor.get().post("metood1",new Object());

        long l4 =  System.currentTimeMillis();

        Log.d("tag", ">>>RxEventProcessor发送耗时---" + (l4-l3));
    }
    @Recipient(tag = "metood1",subscribeOn = ThreadType.NEW)
    public void metood0(Object foods) {
    }
    @Recipient(tag = "metood1",subscribeOn = ThreadType.NEW)
    public void metood1(Object foods) {
    }
    @Recipient(tag = "metood1",subscribeOn = ThreadType.NEW)
    public void metood2(Object foods) {
    }
    @Recipient(tag = "metood1",subscribeOn = ThreadType.NEW)
    public void metood3(Object foods) {
    }
    @Recipient(tag = "metood1",subscribeOn = ThreadType.NEW)
    public void metood4(Object foods) {
    }
    @Recipient(tag = "metood1",subscribeOn = ThreadType.NEW)
    public void metood5(Object foods) {
    }
    @Recipient(tag = "metood1",subscribeOn = ThreadType.NEW)
    public void metood6(Object foods) {
    }
    @Recipient(tag = "metood1",subscribeOn = ThreadType.NEW)
    public void metood7(Object foods) {
    }
    @Recipient(tag = "metood1",subscribeOn = ThreadType.NEW)
    public void metood8(Object foods) {
    }
    @Recipient(tag = "metood1",subscribeOn = ThreadType.NEW)
    public void metood9(Object foods) {
    }
    @Recipient(tag = "metood1",subscribeOn = ThreadType.NEW)
    public void metood10(Object foods) {
    }
    @Recipient(tag = "metood1",subscribeOn = ThreadType.NEW)
    public void metood11(Object foods) {
    }
    @Recipient(tag = "metood1",subscribeOn = ThreadType.NEW)
    public void metood12(Object foods) {
    }
    @Recipient(tag = "metood1",subscribeOn = ThreadType.NEW)
    public void metood13(Object foods) {
    }
    @Recipient(tag = "metood1",subscribeOn = ThreadType.NEW)
    public void metood14(Object foods) {
    }
    @Recipient(tag = "metood1",subscribeOn = ThreadType.NEW)
    public void metood16(Object foods) {
    }
    @Recipient(tag = "metood1",subscribeOn = ThreadType.NEW)
    public void metood17(Object foods) {
    }
    @Recipient(tag = "metood1",subscribeOn = ThreadType.NEW)
    public void metood18(Object foods) {
    }
    @Recipient(tag = "metood1",subscribeOn = ThreadType.NEW)
    public void metood19(Object foods) {
    }
    @Recipient(tag = "metood1",subscribeOn = ThreadType.NEW)
    public void metood20(Object foods) {
    }
    @Recipient(tag = "metood1",subscribeOn = ThreadType.NEW)
    public void metood15(Object foods) {
    }
}
