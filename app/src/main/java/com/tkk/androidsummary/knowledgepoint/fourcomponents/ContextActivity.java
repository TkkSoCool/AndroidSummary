package com.tkk.androidsummary.knowledgepoint.fourcomponents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tkk.androidsummary.R;
import com.tkk.androidsummary.annotation.BindLayout;
import com.tkk.androidsummary.annotation.KnowledgeInfo;
import com.tkk.androidsummary.base.BaseActivity;
/**
 * Created  on 2017/11/14
 * @author 唐开阔
 * @describe Context详解
 * http://blog.csdn.net/guolin_blog/article/details/47028975
 */
@BindLayout(R.layout.activity_context)
@KnowledgeInfo(catalog = KnowledgeInfo.FOUR_COMPONENTS, desc = "Context")
public class ContextActivity extends BaseActivity {
    Object[] objects = new Object[6];
    @Override
    protected void initView() {

    }
}
