package me.yiyou.suanguaming.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import me.yiyou.suanguaming.Tiku

// 数据库创建
@Database(entities = [Tiku::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tikuDao(): TikuDao

    companion object {
        // 单例，防止数据库实例多次创建
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "tiku"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}