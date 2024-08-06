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
    val nowtime: String?,
    @ColumnInfo(name = "content")
    val content : String?,
    @ColumnInfo(name = "type")
    val mtype : Int?,    // 1 : 年月日时,2 : 报数, 3: 随机
    @ColumnInfo(name = "inputnumber")
    val inputnumber : Int?,
    @ColumnInfo(name = "hournumber")
    val hournumber : Int?,
    @ColumnInfo(name = "shangnumber")
    val shangnumber : Int?,
    @ColumnInfo(name = "xianumber")
    val xianumber : Int?,
): Parcelable