package com.tkk.androidsummary.bean;

import android.content.ComponentName;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created  on 2017/11/8
 * @author 唐开阔
 * @describe 每一个知识点对应的activity
 */

public class ActivityData {
    //intent跳转信息
    private ComponentName componentName;
    public ComponentName getComponentName() {
        return componentName;
    }

    public ActivityData(ComponentName componentName, String catalog, String desc) {
        this.componentName = componentName;
        this.desc = desc;
        this.catalog = catalog;
    }

    public void setComponentName(ComponentName componentName) {
        this.componentName = componentName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    //描述
    private String desc;

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    //所属目录
    private String catalog;
}
