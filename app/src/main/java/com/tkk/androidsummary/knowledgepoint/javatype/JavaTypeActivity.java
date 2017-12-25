package com.tkk.androidsummary.knowledgepoint.javatype;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.tkk.androidsummary.R;
import com.tkk.androidsummary.annotation.BindLayout;
import com.tkk.androidsummary.annotation.KnowledgeInfo;
import com.tkk.androidsummary.base.BaseActivity;
import com.tkk.androidsummary.knowledgepoint.javatype.a.Student;
import com.tkk.androidsummary.knowledgepoint.javatype.a.StudentDao;
import com.tkk.androidsummary.knowledgepoint.javatype.a.StudentService;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Java泛型。
 */
@BindLayout(R.layout.activity_java_type)
@KnowledgeInfo(catalog = KnowledgeInfo.JAVA, desc = "Java泛型")
public class JavaTypeActivity extends BaseActivity {
    @Override
    protected void initView() {
        BasePTest<StudentService,Student> basePTest = new BasePTest<StudentService,Student>();
    }
}
