package com.tkk.androidsummary.knowledgepoint.javatype;

import android.util.Log;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created  on 2017/12/5
 *
 * @author 唐开阔
 * @describe
 */

public  class BaseTypeTest<K, T> {
    public BaseTypeTest() {
        getTypeClass();
    }
    public void getTypeClass() {
        ParameterizedType parameterizedType = ((ParameterizedType) getClass().getGenericSuperclass());
        Type type1 = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        Type type2 = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[2];
        System.out.print(type1);
        System.out.print(type2);
    }
}
