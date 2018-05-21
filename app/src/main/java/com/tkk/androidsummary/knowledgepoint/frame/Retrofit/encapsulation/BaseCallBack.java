package com.tkk.androidsummary.knowledgepoint.frame.Retrofit.encapsulation;


import com.tkk.androidsummary.knowledgepoint.frame.CommonResult;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Created  on 2018-05-17
 *
 * @author 唐开阔
 * @describe
 */
public abstract class BaseCallBack<T> implements Observer<CommonResult<T>> {

    @Override
    public void onSubscribe(Disposable d) {
        
    }
    @Override
    public void onNext(CommonResult<T> tCommonResult) {
        if (HttpConfig.RESPONSE_OK == tCommonResult.getStatus()){
            onCallBackSuccess(tCommonResult.getBody());
        }else {
            onFail(tCommonResult.getMessage());
        }
    }
    @Override
    public void onError(Throwable e) {
        onFail(e.getMessage());
    }
    @Override
    public void onComplete() {
    }
    /**
     * 数据请求成功
     * @param data
     */
    public abstract void onCallBackSuccess(T data);
    /**
     * 数据请求失败
     * @param mes
     */
    public abstract void onFail(String mes);
}
