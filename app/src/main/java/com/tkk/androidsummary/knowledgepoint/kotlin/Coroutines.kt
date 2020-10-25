package com.tkk.androidsummary.knowledgepoint.kotlin

import android.util.Log
import kotlinx.coroutines.*
import java.lang.Exception

/**
 * Created  on 2020-06-18
 * @author 唐开阔
 * @describe 协程相关知识点
 * 1：suspend 关键字
 */
class Coroutines {

    //1: 创建协程
    var coroutineScope1: CoroutineScope
    var job: Job? = null

    init {
        coroutineScope1 = CoroutineScope(Dispatchers.Main)

    }


    /**
     * 模拟耗时操作-打印数据
     */
    public suspend fun loadData(s: String) = withContext(Dispatchers.Default) {
        Log.d("Coroutines", "开始请求: $s")
        job?.cancel()
        job = launch(Dispatchers.Default) {
            try {
                Log.d("Coroutines", "test2: $s i = $")
                delay(1500L) // 等待一段时间
                Log.d("Coroutines", "请求完毕: $s")
            } catch (e : Exception){
                Log.d("Coroutines", "异常: $s")
            }
            finally {
            }

        }


    }

}




