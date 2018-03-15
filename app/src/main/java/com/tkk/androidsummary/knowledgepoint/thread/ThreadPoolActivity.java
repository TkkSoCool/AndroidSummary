package com.tkk.androidsummary.knowledgepoint.thread;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tkk.androidsummary.R;
import com.tkk.androidsummary.annotation.BindLayout;
import com.tkk.androidsummary.annotation.KnowledgeInfo;
import com.tkk.androidsummary.base.BaseActivity;

import java.util.concurrent.ThreadPoolExecutor;

@BindLayout(R.layout.activity_thread_pool)
@KnowledgeInfo(catalog = KnowledgeInfo.THREAD, desc = "线程池")
/**
 * 常见的四种线程池
 */
public class ThreadPoolActivity extends BaseActivity {

    @Override
    protected void initView() {
        ThreadPoolExecutor a;
    }
}
