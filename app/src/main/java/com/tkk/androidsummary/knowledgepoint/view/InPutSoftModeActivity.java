package com.tkk.androidsummary.knowledgepoint.view;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ScrollView;

import com.tkk.androidsummary.R;
import com.tkk.androidsummary.annotation.BindLayout;
import com.tkk.androidsummary.annotation.KnowledgeInfo;
import com.tkk.androidsummary.base.BaseActivity;
import com.tkk.androidsummary.util.KeyboardHeightProvider;

import butterknife.BindView;
import butterknife.ButterKnife;

@BindLayout(R.layout.activity_in_put_soft_mode)
@KnowledgeInfo(catalog = KnowledgeInfo.VIEW, desc = "InPutSoftMode")
public class InPutSoftModeActivity extends BaseActivity {

    @BindView(R.id.screen_view)
    ScrollView mScreenView;
    @BindView(R.id.edit)
    EditText mEdit;

    @Override
    protected void initView() {
        new KeyboardHeightProvider(this).init().setHeightListener((isShow, height) -> {
            Log.d(TAG, ">>>initView = " + height + " isShow = " + isShow);
            if (isShow) {
//                mEdit.getL
                mScreenView.post(() -> {
                    ViewCompat.offsetTopAndBottom(mScreenView,-height);
//                    mScreenView.scrollBy(0,-height);
                });
            }else {
                ViewCompat.offsetTopAndBottom(mScreenView,height);

            }
        });
    }

    /**
     * 键盘开启和关闭后，手动计算 mScreenView 或者 scrollBy的逻辑
     * 1：
     */

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void initViewPre() {
        Window window = getWindow();
        //设置透明状态栏,这样才能让 ContentView 向上
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //设置状态栏颜色
        int statusColor = Color.parseColor("#008000");

        window.setStatusBarColor(statusColor);
        ViewGroup mContentView = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
        View mChildView = mContentView.getChildAt(0);
        if (mChildView != null) {
            //注意不是设置 ContentView 的 FitsSystemWindows, 而是设置 ContentView 的第一个子 View .
            // 使其不为系统 View 预留空间.不预留空间的话 状态栏就会覆盖布局顶部
            ViewCompat.setFitsSystemWindows(mChildView, false);
        }
    }


}
