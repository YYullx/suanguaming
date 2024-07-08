package me.yiyou.suanguaming.database

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import me.yiyou.suanguaming.Tiku

class TikuRepository(private val tikuDao: TikuDao) {
    @WorkerThread   // 插入
    suspend fun insert(tiku: Tiku) {
        tikuDao.insert(tiku)
    }
    // 查询
    val allList : Flow<List<Tiku>> = tikuDao.getDetailAll()

    // 删除
    @WorkerThread
    suspend fun delete(tiku: Tiku) {
        tikuDao.delete(tiku)
    }
}