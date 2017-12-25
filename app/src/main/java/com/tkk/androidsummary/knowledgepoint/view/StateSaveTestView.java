package com.tkk.androidsummary.knowledgepoint.view;

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * Created  on 2017/11/14
 * @author 唐开阔
 * @describe 状态保存测试TextView
 */

public class StateSaveTestView extends android.support.v7.widget.AppCompatTextView{
    String TAG = "StateSaveTestView";
    public StateSaveTestView(Context context) {
        super(context);
    }

    public StateSaveTestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StateSaveTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Log.d(TAG, ">>>onSaveInstanceState---" );
        return super.onSaveInstanceState();
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        Log.d(TAG, ">>>onRestoreInstanceState---" );
        super.onRestoreInstanceState(null);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        Log.d(TAG, ">>>setText---  " +text);
        super.setText(text, type);
    }
}
