package com.tkk.androidsummary.knowledgepoint.optimize;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.tkk.androidsummary.R;
import com.tkk.androidsummary.annotation.BindLayout;
import com.tkk.androidsummary.annotation.KnowledgeInfo;
import com.tkk.androidsummary.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;



/**
 * java 虚拟机内存模型
 * a)程序计数器：线程私有，主要用于保存程序执行的位置，如果线程执行的方法不是Native方法，
     则程序计数器保存正在执行的字节码指令地址，如果是Native方法则程序计数器的值则为空（Undefined）。
   b)虚拟机栈：线程私有，存储线程中Java方法调用的状态。当线程调用一个Java方法时，
     虚拟机压入一个新的栈帧到该线程的Java栈中，当该方法执行完成，这个栈帧就从Java栈中弹出。
   c)本地方法栈：类似与b,保存Native方法的信息
   d)堆区：所有线程共享内存区域，用来存放对象实例。被垃圾回收器管理
   e)方法区：线程共享。用来存储已经被Java虚拟机加载的类的结构信息
 * Java对象的四种引用类型
   a)强引用： 直接创建赋值永远不会被垃圾回收，JVM宁愿抛出OutOfMemory错误也不会回收这种对象。
   b)弱引用： 无论内存是否充足，都会回收被弱引用关联的对象。
     WeakReference<Object>reference=new WeakReference<Object>(new Object());
   c)软引用： 内存不足时才会回收，SoftReference来表示一个软引用。
   c)虚引用：一个只具有虚引用的对象，相当于没有被引用，被垃圾收集器回收时会收到一个系统通知
 * GC 垃圾回收器
 * a)概述：GC主要做了两个工作，一个是内存的划分和分配，一个是对垃圾进行回收。
 * b)垃圾回收算法：标记算法
      1)引用计数法：每个对象都有一个引用计数器，被引用时+1,实效-1，当计数器为0时，该对象就是垃圾
      2)根搜索算法：选定一些对象作为GC Roots，并组成根对象集合，然后从这些作为GC Roots的对象作为起始点，
        向下进行搜索，如果目标对象到GC Roots是连接着的，我们则称该目标对象是可达的，否则回收。
 * 内存泄漏：内存泄漏就是指没有用的对象到GC Roots是可达的（对象被引用），导致GC无法回收该对象。
 * 常见的内存泄漏：
    a)静态对象使用不当：static对象常驻内存，如果静态变量持有对象，那么这个对象将不会被GC。
    b)非静态内部类的静态对象：因为非静态内部类默认持有外部类的引用，如果该内部类对象是静态的就会持有外部类的引用，导致无法回收
    c)运行状态下的Thread会作为GC Roots，所以当线程持有需要的对象时也会内存泄漏
    d)Handler导致的内存泄漏，Handler 是非静态的匿名内部类的实例，它会隐性引用外部类HandlerActivity 。
        当activity finish的时候如果还有未处理的消息，此时activity将不会被回收。
      解决：解决方法就是要使用一个静态的Handler内部类，Handler持有的对象要使用弱引用，
         并且在Activity的Destroy方法中移除MessageQueue中的消息
 */
/**
 * Created  on 2017/11/14
 * @author 唐开阔
 * @describe 内存泄漏---内存溢出
 */
@BindLayout(R.layout.activity_memor_leak)
@KnowledgeInfo(catalog = KnowledgeInfo.OPTIMIZE, desc = "内存泄漏")
public class MemorLeakActivity extends BaseActivity {
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // ...
            Toast.makeText(MemorLeakActivity.this, "收到消息", Toast.LENGTH_SHORT).show();

        }
    };
    @Override
    protected void initView() {
        Log.d(TAG, ">>>initView---" + "创建");
    }

    /**
     * 单利持有activity对象
     */
    @OnClick(R.id.bt1)
    void singleton(){
        SingletonTest.getInstance(this);
        changliangci();
    }

    @OnClick(R.id.bt3)
    void handler(){
        Message message = Message.obtain();
        mHandler.sendMessageDelayed(message,10000);
    }
    /**
     * 常量池oom
     */
    void changliangci(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<String> list = new ArrayList<String>();
                int i = 0;
                while (true) {
                    list.add(String.valueOf((i++)).intern());
                }
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, ">>>onDestroy---" + "销毁");
        System.gc();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, ">>>onRestart---" + "重启");
    }
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        Log.d(TAG, ">>>finalize---" + "回收了");
    }
}
