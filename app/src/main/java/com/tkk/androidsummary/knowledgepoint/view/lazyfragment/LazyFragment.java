package com.tkk.androidsummary.knowledgepoint.view.lazyfragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.ViewStubCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import butterknife.Unbinder;

/**
 * Created  on 2018/1/16
 *
 * @author 唐开阔
 * @describe 懒加载fragment
 */

public abstract class LazyFragment extends Fragment{
    // Fragment的根View
    private View mRootView;
    // 检测声明周期中，是否已经构建视图
    private boolean mViewCreated = false;
    // 占位图
    private ViewStubCompat mViewStub;
    @SuppressLint("RestrictedApi")
    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView != null) {
            mViewCreated = true;
            return mRootView;
        }
        final Context context = inflater.getContext();
        FrameLayout root = new FrameLayout(context);
        mViewStub = new ViewStubCompat(context, null);
        mViewStub.setLayoutResource(getResId());
        root.addView(mViewStub, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        root.setLayoutParams(new ViewGroup.MarginLayoutParams(ViewGroup.MarginLayoutParams.MATCH_PARENT, ViewGroup.MarginLayoutParams.MATCH_PARENT));

        mRootView = root;

        mViewCreated = true;
        if (mUserVisible) {
            realLoad();
        }
        return mRootView;
    }

    private boolean mUserVisible = false;

    @Override
    public final void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        mUserVisible = isVisibleToUser;
        if (mUserVisible && mViewCreated) {
            realLoad();
        }
    }

    // 判断是否已经加载
    private boolean mLoaded = false;

    /**
     * 控制只允许加载一次
     */
    @SuppressLint("RestrictedApi")
    private void realLoad() {
        if (mLoaded) {
            return;
        }

        mLoaded = true;
        onRealViewLoaded(mViewStub.inflate());
    }

    @Override
    public void onDestroyView() {
        mViewCreated = false;
        super.onDestroyView();
    }

    /**
     * 获取真正的数据视图
     *
     * @return
     */
    protected abstract int getResId();

    /**
     * 当视图真正加载时调用
     */
    protected abstract void onRealViewLoaded(View view);

}
