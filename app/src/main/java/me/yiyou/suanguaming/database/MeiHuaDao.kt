package me.yiyou.suanguaming.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import me.yiyou.suanguaming.ui.meihua.MeiHuaBean

@Dao
interface MeiHuaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(meiHuaBean: MeiHuaBean)

    @Query("select * from meihua_table")
    fun getAll(): Flow<List<MeiHuaBean>>

    @Delete
    fun delete(meiHuaBean: MeiHuaBean)
}