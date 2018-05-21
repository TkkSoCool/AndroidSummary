package com.tkk.androidsummary.knowledgepoint.frame.Novate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.tamic.novate.Novate;
import com.tamic.novate.Throwable;
import com.tamic.novate.callback.RxResultCallback;
import com.tkk.androidsummary.R;
import com.tkk.androidsummary.annotation.BindLayout;
import com.tkk.androidsummary.annotation.KnowledgeInfo;
import com.tkk.androidsummary.base.BaseActivity;
import com.tkk.androidsummary.knowledgepoint.frame.Retrofit.Translation1;
import com.tkk.androidsummary.knowledgepoint.frame.Retrofit.YouDao_RxService;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@KnowledgeInfo(catalog = KnowledgeInfo.FRAME, desc = "Novate")
@BindLayout(R.layout.activity_novate)
public class NovateActivity extends BaseActivity {
    @Override
    protected void initView() {
        Map<String, String> headers = new HashMap<>();
        headers.put("apikey", "4545sdsddfd7sds");
        Map<String, String> parameters = new HashMap<>();
        parameters.put("uid", "878787878sdsd");
        Novate novate = new Novate.Builder(this)
                .addParameters(parameters)
                .connectTimeout(28)
                .baseUrl("http://fanyi.youdao.com/")
                .addHeader(headers)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addLog(true)
                .build();
        novate.rxPost("path or url", null, new RxResultCallback<Object>() {
            @Override
            public void onError(Object tag, Throwable e) {

            }

            @Override
            public void onCancel(Object tag, Throwable e) {

            }

            @Override
            public void onNext(Object tag, int code, String message, Object response) {

            }
        });
//        novate.create(YouDao_RxService.class)
//                .getCall("what is you name")
//                .subscribeOn(Schedulers.io())
//                .observeOn(Schedulers.io())
//                .subscribe(new Observer<Translation1>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(Translation1 translation1) {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
    }
}
