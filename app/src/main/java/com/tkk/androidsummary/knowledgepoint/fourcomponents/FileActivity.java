package com.tkk.androidsummary.knowledgepoint.fourcomponents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.tkk.androidsummary.R;
import com.tkk.androidsummary.annotation.BindLayout;
import com.tkk.androidsummary.annotation.KnowledgeInfo;
import com.tkk.androidsummary.base.BaseActivity;

@BindLayout(R.layout.activity_file)
@KnowledgeInfo(catalog = KnowledgeInfo.FOUR_COMPONENTS, desc = "File系统")
public class FileActivity extends BaseActivity {
    @Override
    protected void initView() {
        //私有缓存目录
        String exCacheDir = getExternalCacheDir().toString();
        Log.d(TAG, ">>>私有缓存目录---" + exCacheDir);
        //私有目录
        String cacheDir = getCacheDir().toString();
        Log.d(TAG, ">>>私有目录---" + cacheDir);
    }
}
