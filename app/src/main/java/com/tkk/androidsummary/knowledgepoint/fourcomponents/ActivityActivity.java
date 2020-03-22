package com.tkk.androidsummary.knowledgepoint.fourcomponents;

import android.content.Intent;

import com.tkk.androidsummary.R;
import com.tkk.androidsummary.annotation.BindLayout;
import com.tkk.androidsummary.annotation.KnowledgeInfo;
import com.tkk.androidsummary.base.BaseActivity;

import butterknife.OnClick;

/**
 * Created  on 2017/11/8
 * @author 唐开阔
 * @describe 四大主键-activity
 * acticity生命周期：正常
     启动：onCreate :创建   
          onStart : 可见，未在前台，无法交互
          onResume :可见可以交互，已经显示
     停止：onPause：正在停止
          onStop : onPause执行完毕，不可见
     重启：onRestart：重新启动。
          onStart
          onResume
     销毁：停止--》onDestroy
    数据的保存，业务的取消注册要放在onPause() 因为onStop，onDestroy在异常情况下可能不会执行
   异常：1）横竖屏切换或activity被系统回收时的生命周期
      启动：onStart后调用onRestoreInstanceState恢复数据
      销毁：onStop之前调用onSaveInstanceState保存数据
      2）设置了configChange=“orientation”当屏幕旋转时会调用
        onConfigurationChanged方法方法
 *Fragment生命周期
 *Activity的启动模式
    1)Standard 默认模式：每次启动都会新建
    2)singleTop 栈顶复用模式：打开的activity在栈顶就复，一般用于自己打开自己
    3)singleTask 栈内复用模式:如果存在该实例就复用，并清除该实例上面的所有activity
    4)singleInstance模式:该Activity在整个android系统内存中有且只有一个实例，而且该实例单独尊享一个Task。
 *Intent Flag 与Activity task启动模式
    1)FLAG_ACTIVITY_NEW_TASK
        首先会查找是否存在和被启动的Activity具有相同taskAffinity的任务栈,有就以默认模式压入。没有就新建
    2) FLAG_ACTIVITY_CLEAR_TOP:
        相当于启动模式中的SingleTask
    3)FLAG_ACTIVITY_NO_HISTORY
        不在前台就不保留。task A-B-C(FLAG_ACTIVITY_NO_HISTORY)-D 》》E
        此时task为ABCE
    4)FLAG_ACTIVITY_SINGLE_TOP
        相当于启动模式中的singleTop
 */
@BindLayout(R.layout.activity_activity)
@KnowledgeInfo(catalog = KnowledgeInfo.FOUR_COMPONENTS, desc = "Activity")
public class ActivityActivity extends BaseActivity {
    @Override
    protected void initView() {

    }
    @OnClick(R.id.bt_startb)void stratB(){
        startActivity(new Intent(this,BActivity.class));
    }
}
