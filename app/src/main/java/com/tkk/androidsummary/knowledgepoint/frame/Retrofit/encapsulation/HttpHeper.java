package com.tkk.androidsummary.knowledgepoint.frame.Retrofit.encapsulation;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.tkk.androidsummary.BuildConfig;
import com.tkk.androidsummary.LeakApplication;
import com.tkk.androidsummary.util.DeviceUtil;
import com.tkk.androidsummary.util.SharedPreferencesUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created  on 2018-05-18
 * @author 唐开阔
 * @describe Retrofit封装类
 *
 * 如何取消请求？
 * Rx方式和Call方式
 */
public class HttpHeper {
    private Retrofit retrofit;
    private Object tag;
    private HttpHeper(Object object) {
         retrofit = new Retrofit.Builder()
                .baseUrl(HttpConfig.BASE_URL) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getOkHttpClient())
                .build();
        tag = object;
    }

    public <T> ObservableTransformer<T, T> getThread() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 调用方式伪代码：
     * HttpHeper.from(this).xxxApi.xxx(x,y)
     * return : call  or observble
     * 手动取消 自动 取消
     * 判断是call 还是 observble
     */
    public static HttpHeper form(Object object) {
        HttpHeper httpHeper = new HttpHeper(object);
        return httpHeper;
    }

    public <T> T create(final Class<T> service) {
        return retrofit.create(service);
    }

    private OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //日志拦截器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //添加公共的请求头
        Interceptor headerInterceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request request = originalRequest.newBuilder()
                        .header("devicetype", "device_type_pad")
                        .header("deviceid", DeviceUtil.getAndroidId(LeakApplication.getContext()))
                        .header("appversion", BuildConfig.VERSION_NAME)
                        .header("version", "1.0")
//                        .header("token", SharedPreferencesUtil.get(LeakApplication.getContext(), "token"))
                        .build();
                return chain.proceed(request);
            }
        };
        builder.addInterceptor(headerInterceptor)
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(HttpConfig.HTTP_TIME, TimeUnit.SECONDS)
                .readTimeout(HttpConfig.HTTP_TIME, TimeUnit.SECONDS)
                .writeTimeout(HttpConfig.HTTP_TIME, TimeUnit.SECONDS);
        return builder.build();
    }
}
