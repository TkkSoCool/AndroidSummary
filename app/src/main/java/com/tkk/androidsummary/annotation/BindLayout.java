package com.tkk.androidsummary.annotation;

import android.support.annotation.LayoutRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created  on 2017/11/8
 *
 * @author 唐开阔
 * @describe 绑定activity布局
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BindLayout {
    //绑定布局
    @LayoutRes
    int value();
}
