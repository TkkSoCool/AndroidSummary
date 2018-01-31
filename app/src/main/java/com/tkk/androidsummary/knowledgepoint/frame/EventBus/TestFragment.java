package com.tkk.androidsummary.knowledgepoint.frame.EventBus;

import android.view.View;
import android.widget.TextView;

import com.tkk.androidsummary.R;
import com.tkk.androidsummary.knowledgepoint.view.lazyfragment.LazyFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created  on 2017/12/29
 *
 * @author 唐开阔
 * @describe
 */

public class TestFragment extends LazyFragment {
    public String tag;
    @BindView(R.id.textView5)
    public TextView tv;
    public static TestFragment get(String tag){
        TestFragment fragment = new TestFragment();
        fragment.tag = tag;
        return fragment;
    }

    @Override
    protected int getResId() {
        return R.layout.fragment_test;
    }

    @Override
    protected void onRealViewLoaded(View view) {
        ButterKnife.bind(this,view);
        tv.setText(tag);
    }
}
