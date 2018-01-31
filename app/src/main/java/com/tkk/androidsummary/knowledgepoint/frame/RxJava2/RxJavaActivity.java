package com.tkk.androidsummary.knowledgepoint.frame.RxJava2;

import android.util.Log;

import com.hwangjr.rxbus.RxBus;
import com.tkk.androidsummary.R;
import com.tkk.androidsummary.annotation.BindLayout;
import com.tkk.androidsummary.annotation.KnowledgeInfo;
import com.tkk.androidsummary.base.BaseActivity;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Created  on 2018/1/19
 *
 * @author 唐开阔
 * @describe RxJava基本使用学习
 * 主要用于事件的发送和接收
 * 发送者：
 * 接受者：
 * 线程切换: subscribeOn() 指定的是发送事件的线程, observeOn() 指定的是接收事件的线程.
 * 操作符
 * 1）map：{@link #map()}
 * 2)flatMap:{@link #flatMap()}
 * 3)zip:{@link #zip()}
 * 4)ofType:{@link #ofType()}
 * 5)cast:{@link #cast()}
 * 背压：
 * 1）同步的订阅关系，在同一线程中，订阅者处理了事件，发布者才能继续发送
 * 2）异步的订阅关系：事件的发送和消费没有关系，会存在发送速度过快，事件来不及消费的情况
 * 此时RxJava的事件会保存在一个事件列队中。默认大小128.可能会发生内存溢出的情况。
 * 3）解决方案{@link #flowable()}
 */
@KnowledgeInfo(catalog = KnowledgeInfo.FRAME, desc = "RxJava2")
@BindLayout(R.layout.activity_rxjava)
public class RxJavaActivity extends BaseActivity {
    //订阅时候不主动发送时间，只有手动调用onNext
    /**
     * EventProcessor
     */
    FlowableProcessor<Event> mFlowableProcessor;

    @Override
    protected void initView() {
        /**
         * PublishProcessor:事件发送处理器
         */
        mFlowableProcessor = PublishProcessor.create();
        mFlowableProcessor.toSerialized();
        publishProcessor();
    }

    /**
     * Observable和Observer的基本使用
     */
    void ObservableAndObserver() {
        //被观察者，数据发送者
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            /**
             * @param emitter 发送事件，发送onComplete，onError
             * @throws Exception
             */
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        });
        //观察者，数据接受者
        Observer<Integer> observer = new Observer<Integer>() {
            /**
             * @param d 取消订阅，切断链接
             */
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "subscribe");
            }

            @Override
            public void onNext(Integer value) {
                Log.d(TAG, "" + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "error");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "complete");
            }
        };
        //建立连接
        observable.subscribe(observer);


        //只关心onNext事件接收的观察者
        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {

            }
        };
        observable.subscribe(consumer);
    }

    /**
     * map 操作符的使用
     * 对每一个发送事件进行处理
     */
    void map() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return "This is result " + integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, s);
            }
        });

    }

    /**
     * 将每一个事件转化成一个新的事件发送者，来发送事件
     * 事件是无序的
     */
    void flatMap() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                final List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("I am value " + integer);
                }
                return Observable.fromIterable(list).delay(10, TimeUnit.MILLISECONDS);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, s);
            }
        });
    }

    /**
     * 在发射之前，将数据强制转型
     */
    void cast() {
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            /**
             * @param emitter 发送事件，发送onComplete，onError
             * @throws Exception
             */
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        }).cast(String.class);
        //建立连接
        observable.subscribe(new Consumer<String>() {
            @Override
            public void accept(String integer) throws Exception {

            }
        });
    }

    /**
     * 将多个事件发送者合并成一个，有序，事件数量为事件少的那一个
     * 一个事件发送完毕，另一个不会发送
     */
    void zip() {
        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.d(TAG, "emit 1");
                emitter.onNext(1);
                Thread.sleep(1000);

                Log.d(TAG, "emit 2");
                emitter.onNext(2);
                Thread.sleep(1000);

                Log.d(TAG, "emit 3");
                emitter.onNext(3);
                Thread.sleep(1000);

                Log.d(TAG, "emit 4");
                emitter.onNext(4);
                Thread.sleep(1000);

                Log.d(TAG, "emit complete1");
                emitter.onComplete();
            }
        });

        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                Log.d(TAG, "emit A");
                emitter.onNext("A");
                Thread.sleep(1000);

                Log.d(TAG, "emit B");
                emitter.onNext("B");
                Thread.sleep(1000);

                Log.d(TAG, "emit C");
                emitter.onNext("C");
                Thread.sleep(1000);

                Log.d(TAG, "emit complete2");
                emitter.onComplete();
            }
        });

        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Exception {
                return integer + s;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(String value) {
                Log.d(TAG, "onNext: " + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
            }
        });
    }

    /**
     * 对应的观察者是Subscriber
     * 解决发送者与消费者的事件速度不一致的问题
     * 创建Flowable的第二个参数：表示流速不均匀时的处理方式
     * 1）:BackpressureStrategy.ERROR: 抛异常
     * 2）：BackpressureStrategy.BUFFER：自动扩容，无限。
     */
    void flowable() {
        Flowable<Integer> upstream = Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                Log.d(TAG, "emit 1");
                emitter.onNext(1);
                Log.d(TAG, "emit 2");
                emitter.onNext(2);
                Log.d(TAG, "emit 3");
                emitter.onNext(3);
                Log.d(TAG, "emit complete");
                emitter.onComplete();
            }
        }, BackpressureStrategy.ERROR);

        Subscriber<Integer> downstream = new Subscriber<Integer>() {
            /**
             * @param s 开关。
             */
            @Override
            public void onSubscribe(Subscription s) {
                Log.d(TAG, "onSubscribe");
                s.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "onNext: " + integer);

            }

            @Override
            public void onError(Throwable t) {
                Log.w(TAG, "onError: ", t);
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
            }
        };

        upstream.subscribe(downstream);


    }

    /**
     * 过滤掉其他类型
     */
    void ofType() {
        Observable.just("1", 2, new Object())
                .ofType(Integer.class)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        Log.d(TAG, ">>>ofType---" + o.toString());
                    }
                });
    }

    /**
     * Apollo的事件传递流程测试
     */
    void publishProcessor() {
        Consumer consumer1 = new Consumer<Event>() {
            @Override
            public void accept(Event event) throws Exception {
                Log.d(TAG, ">>>consumer1---" + event.tag);
            }
        };
        Consumer consumer2 = new Consumer<Event>() {
            @Override
            public void accept(Event event) throws Exception {
                Log.d(TAG, ">>>consumer2---" + event.tag);

            }
        };
        Consumer consumer3 = new Consumer<Event>() {
            @Override
            public void accept(Event event) throws Exception {
                Log.d(TAG, ">>>consumer3---" + event.tag);

            }
        };

        mFlowableProcessor.map(new Function<Event, Event>() {
            @Override
            public Event apply(Event event) throws Exception {
                event.tag = "mpa1";
                return event;
            }
        }).
                filter(new Predicate<Event>() {
                    @Override
                    public boolean test(Event event) throws Exception {
                        return false;
                    }
                }).subscribe(consumer1);
        mFlowableProcessor.map(new Function<Event, Event>() {
            @Override
            public Event apply(Event event) throws Exception {
                event.tag = "mpa2";

                return event;
            }
        }).subscribe(consumer2);
        mFlowableProcessor.observeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.single())
                .map(new Function<Event, Event>() {
                    @Override
                    public Event apply(Event event) throws Exception {
                        event.tag = "mpa3";

                        return event;
                    }
                })
                .subscribeWith(new ResourceSubscriber<Event>() {
                    @Override
                    public void onNext(Event event) {

                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        mFlowableProcessor.onNext(new Event("1", "1"));
    }
}
