package com.tkk.androidsummary.knowledgepoint.javatype;

import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created  on 2017/12/5
 *
 * @author 唐开阔
 * @describe
 */

public   class BasePTest<K,T> {
    BasePTest() {
        BaseTypeTest<K,T> baseTypeTest = new BaseTypeTest<K, T>();
        baseTypeTest.getTypeClass();

    }
}
