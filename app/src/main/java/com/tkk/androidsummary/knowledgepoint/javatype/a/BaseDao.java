package com.tkk.androidsummary.knowledgepoint.javatype.a;

import java.lang.reflect.ParameterizedType;

/**
 * Created  on 2017/12/5
 *
 * @author 唐开阔
 * @describe
 */

public class BaseDao<T extends BaseModel> {

    public String className;

    public BaseDao() {
        Class<?> clazz = (Class<?>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
        this.className = clazz.getSimpleName();
    }
}

