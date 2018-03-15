package com.tkk.androidsummary.knowledgepoint.frame.Retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.tkk.androidsummary.R;
import com.tkk.androidsummary.annotation.BindLayout;
import com.tkk.androidsummary.annotation.KnowledgeInfo;
import com.tkk.androidsummary.base.BaseActivity;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



/**
 * Retrofit2使用
 */
@KnowledgeInfo(catalog = KnowledgeInfo.FRAME, desc = "Retrofit2")
@BindLayout(R.layout.activity_retrofit2)
public class Retrofit2Activity extends BaseActivity {
    @Override
    protected void initView() {
        example();
        exampleRx();
    }

    public void exampleRx(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fanyi.youdao.com/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        YouDao_RxService request = retrofit.create(YouDao_RxService.class);
        Observable<Translation1> observable = request.getCall("i have a dream");
        observable.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Observer<Translation1>() {

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Translation1 translation1) {
                        Log.d(TAG, ">>>onSubscribe---" + Thread.currentThread().getName());
                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Log.d(TAG+"Rx方式", ">>>onNext---" + translation1.getTranslateResult().get(0).get(0).getTgt());
                    }
                });
    }

    /**
     * 有道翻译api进行简单翻译
     */
    public void example(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fanyi.youdao.com/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();
        YouDao_Service request = retrofit.create(YouDao_Service.class);
        Call<Translation1> call = request.getCall("I love you");
        call.enqueue(new Callback<Translation1>() {
            @Override
            public void onResponse(Call<Translation1> call, Response<Translation1> response) {
                Log.d(TAG+"传统", ">>>onResponse---" + response.body().getTranslateResult().get(0).get(0).getTgt());
            }
            @Override
            public void onFailure(Call<Translation1> call, Throwable throwable) {
                Log.d(TAG,throwable.getMessage());
            }
        });
    }
}
