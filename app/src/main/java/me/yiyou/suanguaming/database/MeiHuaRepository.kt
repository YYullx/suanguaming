package me.yiyou.suanguaming.database

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import me.yiyou.suanguaming.ui.meihua.MeiHuaBean

class MeiHuaRepository(private val meiHuaDao: MeiHuaDao) {

    // 插入
    @WorkerThread
    suspend fun insert(meiHua: MeiHuaBean) {
        meiHuaDao.insert(meiHua)
    }

    // 查询
    val allList: Flow<List<MeiHuaBean>> = meiHuaDao.getAll()

    // 删除
    @WorkerThread
    suspend fun delete(meiHua: MeiHuaBean) {
        meiHuaDao.delete(meiHua)
    }
}