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
 * sleep(time):使线程转到阻塞状态,倒计时结束后进入就绪状态，如果在synchronized
 * 下不会释放对象的锁
 * wait(): Object方法，只能在synchronized(Obj)代码块下使用，该对象已被线程A获取对象锁的前提下，A进入阻塞状态休眠，并且释放对象锁
 * notify(): 唤醒wait方法进入阻塞状态的线程，并从新获取对象锁
 * yield()方法，交出cpu资源，重回就绪状态，yield方法只能让拥有相同优先级或更高优先级的就绪状态线程运行
 * join()方法,针对于主线程与子线程，cThread.join(),主线程会等子线程结束后再执行
 * 线程安全
 * 1)概述：其实是指多线程环境下对共享资源的访问可能会引起此共享资源的不一致性。
 * 2)解决：是在访问临界资源的代码前面加上一个锁，当访问完临界资源后释放锁，让其他线程继续访问。
 * 3)同步锁：Java中每个对象都有一个内置锁。所以说加锁锁住的都是对象，
 * 当程序运行到synchronized同步方法或代码块时必须获得对象的锁才能执行。
 * a)同步代码块,锁的是synchronized()里面的对象
 * b)同步非静态方法锁住的是当前对象实例
 * c)同步静态方法锁住的是类的class对象
 * 4)volatile关键字修饰变量，会保持变量的一致性,　所谓一致性，是指当一条线程修改了共享变量的值，新值对于其他线程来说是可以立即得知的。
 * 线程池
 */
@BindLayout(R.layout.activity_thread)
@KnowledgeInfo(catalog = KnowledgeInfo.THREAD, desc = "Java多线程")
public class ThreadActivity extends BaseActivity {
    SynchronizedTest testClass;
    MyRunble thread1,thread2;

    @Override
    protected void initView() {
        testClass = new SynchronizedTest();
        thread1 = new MyRunble("thread1");
        thread2 = new MyRunble("thread2");

    }

    @OnClick(R.id.bt_creat_by_thred)
    public void onBtCreatByThredClicked() {

    }

    @OnClick(R.id.bt_creat_by_runble)
    public void onBtCreatByRunbleClicked() {

    }

    /**
     * synchronized修饰同步代码块
     */
    @OnClick(R.id.bt_s1)
    public void onBtS1Clicked() {

    }



    class MyRunble extends Thread {
        private String name;

        public MyRunble(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            Log.d(TAG + "子线程name: " + name, " 子线程ID: " + Thread.currentThread().getId());
            try {
                wait(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class SynchronizedTest {
        Integer i = 1;
        /**
         * 同步代码块测试方法
         */
        public void synchronizedDmk() {
            Log.d(TAG + "   进入含有同步代码块方法", Thread.currentThread().getName());
            synchronized (i) {
                Log.d("进入同步代码块", Thread.currentThread().getName());
                for (int i = 1; i < 10; i++) {
                    Log.d(TAG + "synchronizedDmk", Thread.currentThread().getName() + i);
                }
            }
        }

        public synchronized void synchronizedMentod() {
            for (int i = 1; i < 10; i++) {
                Log.d(TAG + "synchronizedMentod", Thread.currentThread().getName() + i);
            }
        }
    }
}
