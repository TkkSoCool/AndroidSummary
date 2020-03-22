package com.tkk.androidsummary.util;

import android.app.Activity;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.PopupWindow;

/**
 * Created  on 2020-03-22
 *
 * @author 唐开阔
 * @describe
 */
public class KeyboardHeightProvider extends PopupWindow implements ViewTreeObserver.OnGlobalLayoutListener {
    private Activity mActivity;
    private View rootView;
    private HeightListener listener;
    private int heightMax; // 记录popup内容区的最大高度

    public KeyboardHeightProvider(Activity activity) {
        super(activity);
        this.mActivity = activity;
        mScreenHeight =  mActivity.getWindowManager().getDefaultDisplay().getHeight();
        ;

        // 基础配置
        rootView = new View(activity);
        setContentView(rootView);

        // 监听全局Layout变化
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(this);
        setBackgroundDrawable(new ColorDrawable(0));

        // 设置宽度为0，高度为全屏
        setWidth(0);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);

        // 设置键盘弹出方式
        setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
    }

    public KeyboardHeightProvider init() {
        if (!isShowing()) {
            final View view = mActivity.getWindow().getDecorView();
            // 延迟加载popupwindow，如果不加延迟就会报错
            view.post(new Runnable() {
                @Override
                public void run() {
                    showAtLocation(view, Gravity.NO_GRAVITY, 0, 0);
                }
            });
        }
        return this;
    }

    public KeyboardHeightProvider setHeightListener(HeightListener listener) {
        this.listener = listener;
        return this;
    }
    private int mRootViewHeight = 0;
    private int mScreenHeight ;
    boolean isOpen = false;
    int kBeight = 0;
    @Override
    public void onGlobalLayout() {
        Log.d("TAG", ">>>onGlobalLayout---" );

        Rect rect = new Rect();
        rootView.getWindowVisibleDisplayFrame(rect);
        int visibleHeight = rect.height();
        if (mRootViewHeight == 0){
            mRootViewHeight = visibleHeight;
        }
        Log.d("TAG", ">>>onGlobalLayout---visibleHeight = " +visibleHeight + "  mRootViewHeight = " +mRootViewHeight );
        int offset = mRootViewHeight - visibleHeight;
        if (offset > mScreenHeight / 5){
            isOpen = true;
            if (kBeight == 0){
                kBeight = mRootViewHeight - visibleHeight;
            }
            listener.onHeightChanged(true,kBeight);
        }else if (offset == 0 && isOpen){
            isOpen = false;
            listener.onHeightChanged(false,kBeight);

        }

    }

    public interface HeightListener {
        void onHeightChanged(boolean isShow,int height);
    }
}
