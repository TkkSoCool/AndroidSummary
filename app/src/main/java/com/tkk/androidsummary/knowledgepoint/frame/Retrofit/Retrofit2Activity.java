package com.tkk.androidsummary.knowledgepoint.frame.Retrofit;

import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.tkk.androidsummary.BuildConfig;
import com.tkk.androidsummary.LeakApplication;
import com.tkk.androidsummary.R;
import com.tkk.androidsummary.annotation.BindLayout;
import com.tkk.androidsummary.annotation.KnowledgeInfo;
import com.tkk.androidsummary.base.BaseActivity;
import com.tkk.androidsummary.knowledgepoint.frame.CommonResult;
import com.tkk.androidsummary.knowledgepoint.frame.Retrofit.encapsulation.BaseCallBack;
import com.tkk.androidsummary.knowledgepoint.frame.Retrofit.encapsulation.BaseFunction;
import com.tkk.androidsummary.knowledgepoint.frame.Retrofit.encapsulation.HttpHeper;
import com.tkk.androidsummary.util.DeviceUtil;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.Subject;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
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
//        example();
//        exampleRx();
                Map<String, String> map = new HashMap<>();

        final HttpHeper httpHeper = HttpHeper.get(this);
        httpHeper.create(UserService.class).getSqr()
                .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean<List<SqrBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean<List<SqrBean>> listBaseBean) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

//        Map<String, String> map = new HashMap<>();
//        map.put("account", "tkk");
//        map.put("password", "123456");
//        Map<String, String> pramMap = ParmMapUtils.getParmMap();
//        Gson gson = new Gson();
//        pramMap.put("data", gson.toJson(map));
//        httpHeper.create(UserService.class)
//                .userLogin(pramMap)
//                .subscribeOn(Schedulers.io())
//                .flatMap(new BaseFunction<UserInfo, WorkInfo>() {
//                    @Override
//                    public ObservableSource<CommonResult<WorkInfo>> back(UserInfo result) {
//                        return httpHeper.create(UserService.class).getCurrentWork(result.getToken(), ParmMapUtils.getParmMap());
//                    }})
//                .observeOn(AndroidSchedulers.mainThread())
//                .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
//                .subscribe(new BaseCallBack<WorkInfo>() {
//                    @Override
//                    public void onCallBackSuccess(WorkInfo data) {
//
//                    }
//                    @Override
//                    public void onFail(String mes) {
//                        Toast.makeText(Retrofit2Activity.this, mes, Toast.LENGTH_SHORT).show();
//                    }
//                });
    }

    public void exampleRx() {
//        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        //拦截器
//        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
//        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        builder.addInterceptor(httpLoggingInterceptor);
//        //设置公共参数
//        Interceptor addQueryParameterInterceptor = new Interceptor() {
//            @Override
//            public okhttp3.Response intercept(Chain chain) throws IOException {
//                Request originalRequest = chain.request();
//                HttpUrl modifiedUrl = originalRequest.url().newBuilder()
////                        .addQueryParameter("platform", "adnroid")
////                        .addQueryParameter("version", "1.2.0")
//                        .build();
//                Request request = originalRequest.newBuilder().url(modifiedUrl).build();
//                return chain.proceed(request);
//            }
//        };
//        builder.addInterceptor(addQueryParameterInterceptor);
//        //添加公共的请求头
//        Interceptor headerInterceptor = new Interceptor() {
//            @Override
//            public okhttp3.Response intercept(Chain chain) throws IOException {
//                Request originalRequest = chain.request();
//                Request request =  originalRequest.newBuilder()
//                        .header("devicetype", "device_type_pad")
//                        .header("deviceid", DeviceUtil.getAndroidId(LeakApplication.getContext()))
//                        .header("appversion", BuildConfig.VERSION_NAME)
//                        .header("version","1.0")
//                        .build();
//                return chain.proceed(request);
//            }
//        };
//        builder.addInterceptor(headerInterceptor);
//
//        final Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://192.168.199.42:8080/") // 设置 网络请求 Url
//                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .client(builder.build())
//                .build();
//        Map<String,String> map = new HashMap<>();
//        map.put("account", "tkk");
//        map.put("password", "123");
//        Map<String,String> pramMap = ParmMapUtils.getParmMap();
//        Gson gson = new Gson();
//        pramMap.put("data",gson.toJson(map));
//        retrofit.create(UserService.class)
//                .userLogin(pramMap)
//                .observeOn(Schedulers.io())
//                .subscribeOn(Schedulers.io())
//                .flatMap(new Function<CommonResult<UserInfo>, ObservableSource<?>>() {
//                    @Override
//                    public ObservableSource<?> apply(CommonResult<UserInfo> userInfoCommonResult) throws Exception {
//                        return null;
//                    }
//                })
//                .subscribe(new BaseCallBack<UserInfo>() {
//                    @Override
//                    public void onCallBackSuccess(UserInfo data) {
//                        retrofit.create(UserService.class).getCurrentWork(data.getToken(),ParmMapUtils.getParmMap())
//                                .subscribe(new Observer<CommonResult>() {
//                                    @Override
//                                    public void onSubscribe(Disposable d) {
//
//                                    }
//
//                                    @Override
//                                    public void onNext(CommonResult commonResult) {
//
//                                    }
//
//                                    @Override
//                                    public void onError(Throwable e) {
//
//                                    }
//
//                                    @Override
//                                    public void onComplete() {
//
//                                    }
//                                });
//                    }
//
//                    @Override
//                    public void onFail(String mes) {
//
//                    }
//                });

//        YouDao_RxService request = retrofit.create(YouDao_RxService.class);
//        Observable<Translation1> observable = request.getCall("i have a dream");
//        observable.subscribeOn(Schedulers.io())
//                .observeOn(Schedulers.io())
//                .subscribe(new Observer<Translation1>() {
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
//
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(Translation1 translation1) {
//                        Log.d(TAG, ">>>onSubscribe---" + Thread.currentThread().getName());
//                        try {
//                            Thread.sleep(10000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        Log.d(TAG + "Rx方式", ">>>onNext---" + translation1.getTranslateResult().get(0).get(0).getTgt());
//                    }
//                });
    }

    /**
     * 有道翻译api进行简单翻译
     */
    public void example() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fanyi.youdao.com/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();
        YouDao_Service request = retrofit.create(YouDao_Service.class);
        Call<Translation1> call = request.getCall("I love you");
        call.enqueue(new Callback<Translation1>() {
            @Override
            public void onResponse(Call<Translation1> call, Response<Translation1> response) {
                Log.d(TAG + "传统", ">>>onResponse---" + response.body().getTranslateResult().get(0).get(0).getTgt());
            }

            @Override
            public void onFailure(Call<Translation1> call, Throwable throwable) {
                Log.d(TAG, throwable.getMessage());
            }
        });
    }

}
