package me.yiyou.suanguaming

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.versionedparcelable.ParcelField
import kotlinx.android.parcel.Parcelize

data class Gua(
    val gua: List<GuaX>
)

data class GuaX(
    val ming: String,
    val shang: String,
    val xia: String,
    val explain: String
)

@Parcelize
@Entity(tableName = "tiku_table")
data class Tiku(
    @PrimaryKey(autoGenerate = true)
    val uid: Long,
    @ColumnInfo(name = "ming")
    val ming: String,
    @ColumnInfo(name = "shang")
    val shang: String,
    @ColumnInfo(name = "xia")
    val xia: String,
    @ColumnInfo(name = "explain")
    val explain: String
):Parcelable