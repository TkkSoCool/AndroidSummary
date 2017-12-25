package com.tkk.androidsummary.knowledgepoint.fourcomponents;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.tkk.androidsummary.R;
import com.tkk.androidsummary.annotation.BindLayout;
import com.tkk.androidsummary.annotation.KnowledgeInfo;
import com.tkk.androidsummary.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created  on 2017/11/8
 *
 * @author 唐开阔
 * @describe 四大主键-Broadcast广播
 */


/**
 * 定义：广播，是一个全局的监听器，属于Android四大组件之一，主要用于消息的发送和接收。
 主要用于不同组件之间通信（包括应用内 / 不同应用之间）与 Android 系统在特定情况下的通信
 * 实现原理：Android中的广播使用了设计模式中的观察者模式：基于消息的发布/订阅事件模型
    a)消息订阅者（广播接收者）BroadcastReceiver
    b)消息发布者（广播发布者）
    c)消息中心
 *  广播接收器BroadcastReceiver
    a)注册
        1)静态注册：在AndroidManifest.xml里通过<receive>标签声明，常驻，不受任何生命周期影响
        2)动态注册：在代码里注册，可以手动销毁，灵活。
 *  广播的分类
    a)有序广播：按照Priority属性值从大-小排序；Priority属性相同者，动态注册的广播优先；先接收的广播接收者可以对广播进行截断和修改
    b)粘性广播: Android5.0 & API 21中已经失效，所以不建议使用，发送广播时，接受者没有注册，当注册后依然可以收到
    c)普通广播：Android5.0 & API 21中已经失效，所以不建议使用
    d)App应用内广播：指定包名，注册广播时将exported属性设置为false，使用封装好的LocalBroadcastManager类(只能动态注册)
    e)系统广播: 电量，开关机，网络连接情况等
 */

@BindLayout(R.layout.activity_broadcast_receiver)
@KnowledgeInfo(catalog = KnowledgeInfo.FOUR_COMPONENTS, desc = "Broadcast")
public class BroadcastActivity extends BaseActivity {
    TestBroadcastReceiver broadcastReceiver;
    @Override
    protected void initView() {

    }


    @OnClick(R.id.bt_rg)
    public void onBtRgClicked() {

    }

    @OnClick(R.id.bt_un_rg)
    public void onBtUnRgClicked() {
    }

    void rgReceiver(){
        broadcastReceiver = new TestBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(broadcastReceiver, intentFilter);
    }
    void unRgReceiver(){
        if (broadcastReceiver!=null) {
            unregisterReceiver(broadcastReceiver);
        }
    }
    public class TestBroadcastReceiver extends BroadcastReceiver {
        String TAG = "TestBroadcastReceiver";

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, ">>>onReceive---" + "接收到了广播");
            Toast.makeText(BroadcastActivity.this, "11", Toast.LENGTH_SHORT).show();
        }
    }
}
