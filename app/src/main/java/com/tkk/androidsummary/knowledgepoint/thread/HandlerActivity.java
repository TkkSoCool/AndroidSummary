package com.tkk.androidsummary.knowledgepoint.thread;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.tkk.androidsummary.R;
import com.tkk.androidsummary.annotation.BindLayout;
import com.tkk.androidsummary.annotation.KnowledgeInfo;
import com.tkk.androidsummary.base.BaseActivity;

/**
 * Created  on 2017/11/14
 * @author 唐开阔
 * @describe handler机制
 */

/**
 * handler机制介绍
 * Message：一个消息的具体封装对象,都会关联一个handler对象
 * Looper：每一个线程都必须持有一个Looper对象才能创建handler对象，调用 Looper.prepare()方法创建。
 * MessageQueue: 消息列队，初始化Looper的时候会构造一个MessageQueue对象。
 * 流程简述：
 * 1)Looper.prepare();给当前线程设置唯一的Looper对象，并初始化MessageQueue
 * 2):handler 所有的发送消息最终都是将一个Message加入到MessageQueue中、按照发送时间的先后顺序
 * 3)Looper.loop()就是通过一个死循环不断的从消息列队中读取消息，然后调用handler.dispatchMessage进行消息的分发
 */
@BindLayout(R.layout.activity_handler)
@KnowledgeInfo(catalog = KnowledgeInfo.THREAD, desc = "Handler机制")
public class HandlerActivity extends BaseActivity {
    private Handler mHandler = new Handler();
    @Override
    protected void initView() {
        mHandler.sendEmptyMessage(1);
        creadHanderInWockThread();
    }

    /**
     * 在子线程中创建一个handler
     */

    private void creadHanderInWockThread(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Log.d(TAG, ">>>run---" + "step1");
                Handler handler = new Handler();
                Log.d(TAG, ">>>run---" + "step2");
                Looper.loop();
                Log.d(TAG, ">>>run---" + "step3");

            }
        }).start();
    }
}
