package me.yiyou.suanguaming.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import me.yiyou.suanguaming.Tiku

@Dao
interface TikuDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(tiku: Tiku)

    @Query("SELECT * FROM tiku_table")
    fun getDetailAll(): Flow<List<Tiku>>

    @Delete
    fun delete(tiku: Tiku)
}