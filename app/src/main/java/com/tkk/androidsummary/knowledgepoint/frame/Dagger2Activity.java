package com.tkk.androidsummary.knowledgepoint.frame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tkk.androidsummary.R;

import javax.inject.Inject;

public class Dagger2Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger2);
    }
}
