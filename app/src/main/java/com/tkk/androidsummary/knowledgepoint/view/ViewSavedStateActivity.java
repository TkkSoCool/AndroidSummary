package com.tkk.androidsummary.knowledgepoint.view;

import android.os.Bundle;
import android.text.InputFilter;
import android.widget.EditText;

import com.tkk.androidsummary.R;
import com.tkk.androidsummary.annotation.BindLayout;
import com.tkk.androidsummary.annotation.KnowledgeInfo;
import com.tkk.androidsummary.base.BaseActivity;
import com.tkk.androidsummary.util.MoneyInputFilter;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created  on 2017/11/8
 *
 * @author 唐开阔
 * @describe View-view状态的保存
 * acticity生命周期：正常
 * Activity类的onSaveInstanceState默认实现会恢复Activity的状态，
 * 默认实现会为布局中的每个View调用相应的 onSaveInstanceState方法，让每个View都能保存自身的信息。
 * 自定义view要设置id和setSaveEnabled(true)
 **/
@BindLayout(R.layout.activity_view_saved_state)
@KnowledgeInfo(catalog = KnowledgeInfo.VIEW, desc = "View的状态保存")
public class ViewSavedStateActivity extends BaseActivity {

    @BindView(R.id.textView)
    StateSaveTestView textView;
    @BindView(R.id.edit)
    EditText editText;
    @Override
    protected void initView() {
        textView.setText("1");
        editText.setFilters(new InputFilter[]{new MoneyInputFilter()});
    }
}
