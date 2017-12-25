package com.tkk.androidsummary.knowledgepoint.touch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * Created  on 2017/11/22
 *
 * @author 唐开阔
 * @describe
 */

public class TestButton extends Button {
    public TestButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d("TouchActivity", ">>>dispatchTouchEvent---" + event.getAction() );
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("TouchActivity", ">>>onTouchEvent---" + event.getAction());
        return false;
    }
}
