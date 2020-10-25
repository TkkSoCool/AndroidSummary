package com.tkk.androidsummary.knowledgepoint.kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.tkk.androidsummary.R
import com.tkk.androidsummary.annotation.BindLayout
import com.tkk.androidsummary.annotation.KnowledgeInfo
import com.tkk.androidsummary.base.BaseActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
@BindLayout(R.layout.activity_kotlin_activity)
@KnowledgeInfo(catalog = KnowledgeInfo.ALGROITHM, desc = "Kotlin")
class KotlinActivityActivity : BaseActivity() {
    var coroutines  = Coroutines()
    override fun initView() {
        CoroutineScope(Dispatchers.Main).launch {
            coroutines.loadData("请求1")
            kotlinx.coroutines.delay(500)
            coroutines.loadData("请求2")
            kotlinx.coroutines.delay(500)
            coroutines.loadData("请求3")
        }
    }


}
