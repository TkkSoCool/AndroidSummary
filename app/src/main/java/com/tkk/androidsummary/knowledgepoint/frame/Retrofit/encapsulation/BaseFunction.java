package com.tkk.androidsummary.knowledgepoint.frame.Retrofit.encapsulation;

import com.tkk.androidsummary.knowledgepoint.frame.CommonResult;
import com.tkk.androidsummary.knowledgepoint.frame.Retrofit.WorkInfo;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
/**
 * Created  on 2018-05-18
 * @author 唐开阔
 * @param <T> 接受的类型
 * @param <R> 返回的类型
 * @describe 用于RxJava-Retrofit链式调用 flatMap 操作符统一处理
 */
public abstract class BaseFunction<T, R> implements Function<CommonResult<T>, ObservableSource<CommonResult<R>>> {
    @Override
    public ObservableSource<CommonResult<R>> apply(CommonResult<T> tCommonResult) throws Exception {
        if (HttpConfig.RESPONSE_OK == tCommonResult.getStatus()){
            return  back(tCommonResult.getBody());
        }else {
            return Observable.error(new Throwable(tCommonResult.getMessage()));
        }
    }
    public abstract ObservableSource<CommonResult<R>> back(T result);
}
