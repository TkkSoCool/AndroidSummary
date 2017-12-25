package com.tkk.androidsummary.knowledgepoint.fourcomponents;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;

import com.tkk.androidsummary.R;
import com.tkk.androidsummary.annotation.BindLayout;
import com.tkk.androidsummary.annotation.KnowledgeInfo;
import com.tkk.androidsummary.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created  on 2017/11/8
 * @author 唐开阔
 * @describe 四大主键-Service
 */

/**
 * Service简绍
    1)定义：Android四大组件之一，不依赖UI，它主要用于在后台处理一些耗时的逻辑，或者去执行某些需要长期运行的任务。
    2)Service和Thread的区别
       不依赖页面，可独立运行，有自己的生命周期。多个activity都可以绑定控制，比Thread方便控制
 * 开启一个Service的两种方式
    1)startService 只是启动Service，启动它的组件（如Activity）和Service并没有关联，只有当Service调用stopSelf或者其他组件调用stopService服务才会终止。
    2)bindService  其他组件可以通过回调获取Service的代理对象和Service交互，而这两方也进行了绑定，当启动方销毁时，
      Service也会自动进行unBind操作，当发现所有绑定都进行了unBind时才会销毁Service。
 * IntentService
    1)定义：IntentService是Android里面的一个封装类，继承自四大组件之一的Service。
    2)作用：处理异步请求，实现多线程
    3)使用流程：a)不建议使用bindService方法使用
               b)只有一个工作线程，在oncreat时创建
               c)通过onStartCommand()传递给服务intent，依次插入到工作队列中，并逐个发送给onHandleIntent()
                工作任务队列是顺序执行的。
                d)当intent列队执行完毕后自动关闭


 */
@BindLayout(R.layout.activity_service_test2)
@KnowledgeInfo(catalog = KnowledgeInfo.FOUR_COMPONENTS, desc = "Service")
public class ServiceTestActivity extends BaseActivity {
    @Override
    protected void initView() {

    }
    @OnClick(R.id.bt_staret)
    public void onBtStaretClicked() {
        Intent intent = new Intent(this,MyTestService.class);
        startService(intent);
    }

    @OnClick(R.id.bt_stop)
    public void onBtStopClicked() {
        Intent intent = new Intent(this,MyTestService.class);
        stopService(intent);
    }

    @OnClick(R.id.bt_bind)
    public void onBtBindClicked() {
        Intent intent = new Intent(this,MyTestService.class);
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
            }
            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        },BIND_AUTO_CREATE);
    }

    @OnClick(R.id.bt_unbind)
    public void onBtUnbindClicked() {
    }
}
