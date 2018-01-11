package com.tkk.androidsummary.knowledgepoint.frame.EventBus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lsxiao.apollo.core.Apollo;
import com.tkk.androidsummary.R;

import org.greenrobot.eventbus.EventBus;

/**
 * Created  on 2017/12/29
 *
 * @author 唐开阔
 * @describe
 */

public class TestFragment extends Fragment {
    public String tag;
    public static TestFragment get(String tag){
        TestFragment fragment = new TestFragment();
        fragment.tag = tag;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_eventbus_test,container,false);
        view.findViewById(R.id.btm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Apollo.emit("event",tag);

            }
        });
        return view;
    }
}
