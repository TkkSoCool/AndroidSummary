package com.tkk.androidsummary.knowledgepoint.javatype.a;

/**
 * Created  on 2017/12/5
 *
 * @author 唐开阔
 * @describe
 */

public class BaseService<T extends BaseModel> {
    public BaseDao<T> baseDao = new BaseDao<T>();
}