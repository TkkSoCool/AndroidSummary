package com.tkk.androidsummary.knowledgepoint.frame.Lifecycle;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.tkk.androidsummary.base.BaseFragment;

/**
 * Created  on 2018-05-17
 *
 * @author 唐开阔
 * @describe
 */
public class LifecycleFragment extends BaseFragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLifecycle().addObserver(new LifecycleImpl());
    }
}
