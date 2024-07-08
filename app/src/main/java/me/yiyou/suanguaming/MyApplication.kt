package me.yiyou.suanguaming

import android.app.Application
import com.kongzue.dialogx.DialogX
import com.kongzue.dialogx.style.IOSStyle
import me.yiyou.suanguaming.database.AppDatabase
import me.yiyou.suanguaming.database.TikuRepository
import net.time4j.android.ApplicationStarter


class MyApplication : Application() {
    private val database by lazy {
        AppDatabase.getDatabase((this@MyApplication))
    }
    val repository by lazy {
        TikuRepository(database.tikuDao())
    }

    override fun onCreate() {
        super.onCreate()
        DialogX.init(this)
        DialogX.globalStyle = IOSStyle()

        // time4a 时间库
        ApplicationStarter.initialize(this, true); // with prefetch on background thread
    }
}