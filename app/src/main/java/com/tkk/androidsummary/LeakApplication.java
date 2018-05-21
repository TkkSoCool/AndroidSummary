package com.tkk.androidsummary;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;


/**
 * Created  on 2017/11/27
 *
 * @author 唐开阔
 * @describe
 */

public class LeakApplication extends Application {
    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
        context = this;
    }
}
