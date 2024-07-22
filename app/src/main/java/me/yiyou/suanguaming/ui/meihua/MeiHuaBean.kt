package me.yiyou.suanguaming.ui.meihua

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.time.LocalDateTime
import java.util.Date

@Parcelize
@Entity(tableName = "meihua_table")
data class MeiHuaBean(
    @PrimaryKey(autoGenerate = true)
    val uid: Long,
    @ColumnInfo(name = "year")
    val year: Int?,
    @ColumnInfo(name = "month")
    val month: Int?,
    @ColumnInfo(name = "day")
    val day: Int?,
    @ColumnInfo(name = "time")
    val time: Int?,
    @ColumnInfo(name = "nowtime")
    val nowtime: String?
): Parcelable