package com.tkk.androidsummary.knowledgepoint.frame.dagger2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.tkk.androidsummary.R;
import com.tkk.androidsummary.annotation.BindLayout;
import com.tkk.androidsummary.annotation.KnowledgeInfo;
import com.tkk.androidsummary.base.BaseActivity;
import com.tkk.androidsummary.base.BaseFragment;
import javax.inject.Inject;

import dagger.internal.DaggerCollections;
import okhttp3.OkHttpClient;

@KnowledgeInfo(catalog = KnowledgeInfo.FRAME, desc = "Dagger2注入")
@BindLayout(R.layout.activity_dagger3)
public class Dagger2Activity extends BaseActivity {
    @Inject
    StudentBean studentBean;
    @Inject
    OkHttpClient okHttpClient;
    @Override
    protected void initView() {
        DaggerActivityComponent.builder().activityModule(new ActivityModule()).build().inject(this);
        Log.d(TAG, "student:" + okHttpClient.toString());
    }
}
