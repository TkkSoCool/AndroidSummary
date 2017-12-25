package com.tkk.androidsummary.annotation;

import android.support.annotation.LayoutRes;
import android.support.annotation.StringDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created  on 2017/11/8
 * @author 唐开阔
 * @describe 知识点信息注解
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface KnowledgeInfo {
    public static  final String IPC = "IPC跨进程通信";
    public static  final String FOUR_COMPONENTS = "四大主键系列";
    public static  final String VIEW = "Android-View系列";
    public static  final String THREAD = "多线程系列";
    public static  final String ALGROITHM = "数据结构与算法";
    public static  final String TOUCH = "事件分发机制";
    public static  final String FRAME = "第三方框架";
    public static  final String OPTIMIZE = "性能优化";
    public static  final String JAVA = "JAVA系列";
    public static  final String JG = "Android架构";

    @StringDef({IPC,FOUR_COMPONENTS,VIEW,THREAD,ALGROITHM,TOUCH,FRAME,OPTIMIZE,JAVA,JG})
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface DefCatalog {
    }

    //一级分类
    @DefCatalog
    String catalog() default "";

    //描述
    String desc() default "";

    //是否显示
    boolean isShow() default true;



}
