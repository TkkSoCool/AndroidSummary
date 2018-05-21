package com.tkk.androidsummary.knowledgepoint.frame.Lifecycle;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.util.Log;

/**
 * Created  on 2018-05-17
 *
 * @author 唐开阔
 * @describe
 */
public class LifecycleImpl implements LifecycleObserver {
    String TAG = "Lifecycle";
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        Log.d(TAG, ">>>onResume---" );
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        Log.d(TAG, ">>>onPause---" );
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreat() {
        Log.d(TAG, ">>>onCreat---" );
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        Log.d(TAG, ">>>onDestroy---" );
    }

}
