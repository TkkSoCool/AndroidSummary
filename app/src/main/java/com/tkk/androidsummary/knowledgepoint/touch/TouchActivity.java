package com.tkk.androidsummary.knowledgepoint.touch;

import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.tkk.androidsummary.R;
import com.tkk.androidsummary.annotation.BindLayout;
import com.tkk.androidsummary.annotation.KnowledgeInfo;
import com.tkk.androidsummary.base.BaseActivity;

import butterknife.OnClick;
import butterknife.OnTouch;
/**
 * Created  on 2017/11/14
 *
 * @author 唐开阔
 * @describe 事件分发机制
 * <p>
 * 事件：是指用户在屏幕上触摸操作
 * <p>
 * 事件：是指用户在屏幕上触摸操作
 */

/**
 * 事件：是指用户在屏幕上触摸操作
 */

/**
 * view的事件分发。
 * 1)dispatchTouchEvent 分发:是否处理这个事件。
 *      a)如果OnTouchListener为空或者返回flase，那么调用onTouchEvent并返回其返回值。
 *      b)OnTouchListener返回为true，方法返回true，不执行onTouchEvent。
 *      c)dispatchTouchEvent在进行事件分发的时候，只有前一个action返回true，才会触发下一个action
 * 2)onTouchEvent 消费:
 *
 * ViewGroup的事件分发：
 * 1)dispatchTouchEvent 分发：是否处理这个事件
 * 2)onInterceptTouchEvent  拦截：是否拦截这个事件自己处理。
 *
 * Activity的事件分发：
 * dispatchTouchEvent：分发
 * 1）如果是按下，调用onUserInteraction方法，
 * 2)接着在dispatchTouchEvent方法中会通过Activity的root View（id为content的FrameLayout），
    实质是ViewGroup，通过super.dispatchTouchEvent把touchevent派发给各个activity的子view，
    也就是我们再Activity.onCreat方法中setContentView时设置的view。
 3)若Activity下面的子view拦截了touchevent事件(返回true)则Activity.onTouchEvent方法就不会执行。
 */
@BindLayout(R.layout.activity_touch)
@KnowledgeInfo(catalog = KnowledgeInfo.TOUCH, desc = "事件分发流程")
public class TouchActivity extends BaseActivity {
    @Override
    protected void initView() {
    }

    @OnTouch(R.id.layout_root)
    boolean onRootTouch(MotionEvent event) {
        Log.d(TAG, ">>>onRootTouch---" + event.getAction());
        return true;
    }

    @OnTouch(R.id.bt_test)
    boolean onBtTouch(MotionEvent event) {
        Log.d(TAG, ">>>onBtTouch---" + event.getAction());
        return false;
    }

    @OnClick(R.id.layout_root)
    void onRootClick() {
        Log.d(TAG, ">>>onRootClick---");

    }

    @OnClick(R.id.bt_test)
    void onBtClick() {
        Log.d(TAG, ">>>onBtClick---");
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
}
