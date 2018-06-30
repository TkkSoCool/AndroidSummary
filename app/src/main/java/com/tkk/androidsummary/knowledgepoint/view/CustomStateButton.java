package com.tkk.androidsummary.knowledgepoint.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;

import com.tkk.androidsummary.R;

/**
 * Created  on 2018/6/27 0027
 * @author 唐开阔
 * @describe 自定义背景状态的Button
 */
public class CustomStateButton extends AppCompatButton {
    private Context mContext;
    private boolean isSelector;

    private int defaultColor;//默认颜色
    private int defaultSelectorColor; //默认被选中颜色
    private int defaultCorners;//默认圆角
    private int defaultStrokeWidth;//默认描边宽度

    private int solidColor;//填充颜色
    private int strokeColor;//描边颜色
    private int solidPressedColor;//按下填充颜色
    private int strokePressedColor;//按下描边颜色
    private float cornersRadius; //圆角
    private int strokeWidth; //描边宽度

    //普通按钮，只关心按下状态
    private int state_pressed = android.R.attr.state_pressed;
    private int state_window_focused = android.R.attr.state_window_focused;
    //是否获取焦点，一般用于输入框
    private int state_focused = android.R.attr.state_focused;
    //是否选中，例如单选框，复选框
    private int state_selected = android.R.attr.state_selected;
    private GradientDrawable gradientDrawable;
    private StateListDrawable stateListDrawable;
    public CustomStateButton(Context context) {
        this(context,null);
    }

    public CustomStateButton(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomStateButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initAttr(attrs);
        setGravity(Gravity.CENTER);
        ViewCompat.setBackground(this,isSelector ? getSelector():getDrawable(0));

    }

    /**
     * 解析配置
     * @param attrs
     */
    private void initAttr(AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.CustomStateButton);
        isSelector = typedArray.getBoolean(R.styleable.CustomStateButton_isSelector,false);
        solidColor = typedArray.getColor(R.styleable.CustomStateButton_solidColor,defaultColor);
        strokeColor = typedArray.getColor(R.styleable.CustomStateButton_strokeColor,defaultColor);
        solidPressedColor = typedArray.getColor(R.styleable.CustomStateButton_solidPressedColor,defaultSelectorColor);
        strokePressedColor = typedArray.getColor(R.styleable.CustomStateButton_strokePressedColor,defaultSelectorColor);
        cornersRadius = typedArray.getDimension(R.styleable.CustomStateButton_cornersRadius,defaultCorners);
        strokeWidth = (int) typedArray.getDimension(R.styleable.CustomStateButton_strokeWidth,defaultCorners);
        typedArray.recycle();
    }
    public StateListDrawable getSelector() {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{16842919, 16842910}, this.getDrawable(16842919));
        stateListDrawable.addState(new int[]{-16842910}, this.getDrawable(-16842910));
        stateListDrawable.addState(new int[0], this.getDrawable(16842910));
        return stateListDrawable;
    }

    /**
     * 设置状态
     * @param state
     * @return
     */
    public GradientDrawable getDrawable(int state) {
        gradientDrawable = new GradientDrawable();
        gradientDrawable.setStroke(strokeWidth,strokeColor);
        gradientDrawable.setColor(solidColor);
        gradientDrawable.setCornerRadius(cornersRadius);
        return gradientDrawable;
    }
}
