package com.tkk.androidsummary.knowledgepoint.mvvm;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tkk.androidsummary.R;
import com.tkk.androidsummary.annotation.KnowledgeInfo;
import com.tkk.androidsummary.base.BaseActivity;
import com.tkk.androidsummary.databinding.ActivityMvvm2Binding;
@KnowledgeInfo(catalog = KnowledgeInfo.JG, desc = "MVVM")
public class Mvvm2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMvvm2Binding binding = DataBindingUtil.setContentView(this,R.layout.activity_mvvm2);
        User user = new User("Test", "User");
        binding.setUser(user);
    }
}
