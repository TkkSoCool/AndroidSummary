package com.tkk.androidsummary.knowledgepoint.fourcomponents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.tkk.androidsummary.R;

public class BActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("BActivity", ">>>onDestroy---" );
    }
}
