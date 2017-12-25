package com.tkk.androidsummary.knowledgepoint.thread;

import android.os.Bundle;
import android.util.Log;

import com.tkk.androidsummary.R;
import com.tkk.androidsummary.annotation.BindLayout;
import com.tkk.androidsummary.annotation.KnowledgeInfo;
import com.tkk.androidsummary.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
/**
 * Created  on 2017/11/14
 * @author 唐开阔
 * @describe 多线程
 */

/**
 * 定义：
 * a)进程：每个运行中的程序就是一个进程
 * b)线程：每个运行的程序（进程）内部可能会包含多个线程，每个线程可以看做一个顺序执行流。
 * c)区别：一个进程是一个独立(self contained)的运行环境，它可以被看作一个程序或者一个应用。
 * 而线程是在进程中执行的一个任务。线程是进程的子集，一个进程可以有很多线程，每条线程并行执行不同的任务。
 * 不同的进程使用不同的内存空间，而所有的线程共享一片相同的内存空间。
 * 别把它和栈内存搞混，每个线程都拥有单独的栈内存用来存储本地数据。
 * <p>
 * 线程的五大状态
 * a)创建：比如新 new 出一个线程，这个线程就处于创建状态
 * b)就绪：调用了start方法，等待cpu进行调度，并不是说调用start就立即执行。
 * c)运行：执行run()方法，真正执行，前提是处于就绪状态
 * d)阻塞（blocked）状态: 暂时停止执行, 可能将资源交给其它线程使用
 * 1)主动等待Thread.sleep(time) 不会释放线程锁，需要别的线程执行notify/notifyAll才能够重新获得CPU执行时间.
 * 2)Object.waiting()（等待被唤醒）,释放锁
 * 3)同步阻塞，需要的锁没有被释放
 * e)终止（dead）状态: 线程销毁
 * Thread的几个重要方法
 * a)start()方法,线程进入就绪状态，等待cpu调度
 * b)run()方法，获取cpu资源线程处于运行状态
 * c)isAlive()方法,是否处于运行状态
 * d)yield()方法，交出cpu资源，重回就绪状态yield方法只能让拥有相同优先级或更高优先级的线程以运行的机会。
 * e)join()方法,join()的作用是等待线程对象销毁。
 * 线程安全
 * 1)概述：其实是指多线程环境下对共享资源的访问可能会引起此共享资源的不一致性。
 * 2)解决：是在访问临界资源的代码前面加上一个锁，当访问完临界资源后释放锁，让其他线程继续访问。
 * 3)同步锁：Java中每个对象都有一个内置锁。所以说加锁锁住的都是对象，
 * 当程序运行到synchronized同步方法或代码块时必须获得对象的锁才能执行。
 * a)同步代码块,锁的是synchronized()里面的对象
 * b)同步非静态方法锁住的是当前对象实例
 * c)同步静态方法锁住的是类的class对象
 * 4)volatile关键字修饰变量，会保持变量的一致性
 * 线程池
 */
@BindLayout(R.layout.activity_thread)
@KnowledgeInfo(catalog = KnowledgeInfo.THREAD, desc = "Java多线程")
public class ThreadActivity extends BaseActivity {
    SynchronizedTest testClass;

    @Override
    protected void initView() {
        testClass = new SynchronizedTest();
    }

    @OnClick(R.id.bt_creat_by_thred)
    public void onBtCreatByThredClicked() {
        MyThread thread = new MyThread("MyThread");
        thread.start();
    }

    @OnClick(R.id.bt_creat_by_runble)
    public void onBtCreatByRunbleClicked() {
        MyRunble runble = new MyRunble("MyRunble");
        Thread thread = new Thread(runble);
        thread.start();
    }

    /**
     * synchronized修饰同步代码块
     */
    @OnClick(R.id.bt_s1)
    public void onBtS1Clicked() {
        for (int i = 1; i < 6; i++) {
            MyThread thread = new MyThread(i + "Thread  ");
            thread.start();
        }

    }

    class MyThread extends Thread {

        public MyThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            testClass.synchronizedDmk();
            testClass.synchronizedMentod();
        }
    }

    class MyRunble implements Runnable {
        private String name;

        public MyRunble(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            Log.d(TAG + "子线程name: " + name, " 子线程ID: " + Thread.currentThread().getId());
        }
    }

    class SynchronizedTest {
        /**
         * 同步代码块测试方法
         */
        public void synchronizedDmk() {
            Log.d(TAG + "   进入含有同步代码块方法", Thread.currentThread().getName());
            synchronized (new Object()) {
                Log.d("进入同步代码块", Thread.currentThread().getName());
                for (int i = 1; i < 10; i++) {
                    Log.d(TAG + "  synchronizedDmk", Thread.currentThread().getName() + i);
                }
            }
        }

        public synchronized void synchronizedMentod() {
            for (int i = 1; i < 10; i++) {
                Log.d(TAG + "  synchronizedMentod", Thread.currentThread().getName() + i);
            }
        }
    }
}
