package com.tkk.androidsummary.knowledgepoint.frame.Luban;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.tkk.androidsummary.R;
import com.tkk.androidsummary.annotation.BindLayout;
import com.tkk.androidsummary.annotation.KnowledgeInfo;
import com.tkk.androidsummary.base.BaseActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;
@KnowledgeInfo(catalog = KnowledgeInfo.FRAME, desc = "Luban")
@BindLayout(R.layout.activity_luban)
public class LubanActivity extends BaseActivity {
    PhotoCompressUtils photoCompressUtils;
    @Override
    protected void initView() {
        photoCompressUtils = new PhotoCompressUtils();
        List<String> paths = new ArrayList<>();
        Observable.fromArray(paths.toArray())
                .cast(String.class)
                .observeOn(Schedulers.io())
                .map(path -> {
                    Log.d(TAG, ">>>onCreate---" + Thread.currentThread().getName());
                    return photoCompressUtils.compress(LubanActivity.this,path);
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, ">>>onNext---" + Thread.currentThread().getName());
                        Log.d(TAG, ">>>onNext---" + s);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luban);
        Luban.with(this)
                .load(new ArrayList<>())
                .ignoreBy(100)
                .setTargetDir("111")
                .filter(path -> !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif")))
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onSuccess(File file) {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                }).launch();

//
//        Observable.just(paths).map(new Function<List<String>, Object>() {
//        })
    }


}
