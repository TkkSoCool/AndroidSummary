package com.tkk.androidsummary.knowledgepoint.frame.Retrofit;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created  on 2018\3\14 0014
 *
 * @author 唐开阔
 * @describe 有道翻译与rxjava的service
 * 使用表单方式提交
 */

public interface YouDao_RxService {
    @POST("translate?doctype=json&jsonversion=&type=&keyfrom=&model=&mid=&imei=&vendor=&screen=&ssid=&network=&abtest=")
    @FormUrlEncoded
    Observable<Translation1> getCall(@Field("i") String targetSentence);
}
