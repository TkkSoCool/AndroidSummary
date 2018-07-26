package com.tkk.androidsummary.knowledgepoint.view.fragment_life;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tkk.androidsummary.R;

import java.util.Random;

/**
 * Created  on 2018-07-14
 *
 * @author 唐开阔
 * @describe Fragment生命周期测试
 * FragmentTransaction 对fragment进行管理
 * 1）add 添加到栈顶:onAttach-onCreate-onCreateView-onActivityCreated-onStart-onResume
 * 2)remove 移除：onPause-onDestroyView-onDestroy
 * 3)hide,show 显示隐藏：不影响生命周期
 * 4)replace 代替：remove其他fragment，再添加自己
 * 5)detach: 将view和fragment分离，并且从fragment栈中移除，但是实例还在：onPause-onDestroyView
 * 6)attach:与detach想反，onCreateView-onActivityCreated-onStart-onResume
 * FragmentPagerAdapter 在切换时主要调用 5,6的方法用于回收view
 *
 *
 */
public class LifeFragment extends Fragment{
    public   String  TAG  = "LifeFragment";
    private String type;

    public static LifeFragment newInstance(String type) {
        Bundle argz = new Bundle();
        argz.putString("LifeFragment", type);
        LifeFragment fragment = new LifeFragment ();
        fragment.setArguments(argz);
        return fragment;
    }
    /**
     * 与父级完成绑定
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, ">>>onAttach---" + context.getClass().getSimpleName());
    }

    /**
     * 对用户是否可见--配合viewpager会调用这个方法
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.d(TAG, ">>>setUserVisibleHint---" + isVisibleToUser);
    }

    /**
     * 创建
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        savedInstanceState = getArguments();
        if (savedInstanceState != null) {
            type =  savedInstanceState.getString("LifeFragment");
        }
        TAG = "LifeFragment" + "  " + type;
        Log.d(TAG, ">>>onCreate---" );
    }

    /**
     * 创建视图
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, ">>>onCreateView---");
        View contentView = inflater.inflate(R.layout.fragment_test, container, false);

        TextView textView = contentView.findViewById(R.id.textView5);
        textView.setText(type);
        Random random = new Random();
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        textView.setBackgroundColor(Color.rgb(r,g,b));
        return contentView;
    }

    /**
     * Activity 的OnCreated执行完毕
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, ">>>onActivityCreated---" );
    }

    /**
     * 不可见变为可见
     */
    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, ">>>onStart---" );
    }

    /**
     * 活动状态。可交互
     */
    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, ">>>onResume---" );
    }

    /**
     * 暂停状态，可见不可交互
     */
    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, ">>>onPause---" );
    }

    /**
     * 销毁视图-配合viewpager会调用这个方法
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, ">>>onDestroyView---" );
    }

    /**
     * 销毁Fragment
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, ">>>onDestroy---" );
    }

    /**
     * 解除绑定
     */
    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, ">>>onDetach---" );
    }
}
