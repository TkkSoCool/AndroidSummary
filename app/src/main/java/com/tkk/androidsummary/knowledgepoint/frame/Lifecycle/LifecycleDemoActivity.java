package com.tkk.androidsummary.knowledgepoint.frame.Lifecycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tkk.androidsummary.R;
import com.tkk.androidsummary.annotation.BindLayout;
import com.tkk.androidsummary.annotation.KnowledgeInfo;
import com.tkk.androidsummary.base.BaseActivity;
@KnowledgeInfo(catalog = KnowledgeInfo.FRAME, desc = "Lifecycle")
@BindLayout(R.layout.activity_lifecycle)
public class LifecycleDemoActivity extends BaseActivity {
    @Override
    protected void initView() {
        getLifecycle().addObserver(new LifecycleImpl());
    }
}
