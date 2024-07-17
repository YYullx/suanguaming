package me.yiyou.suanguaming.ui.meihua

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MeiHuaTools {

    companion object {
        /**
         * 获取当前的时辰数
         */
        @JvmStatic
        fun conversionTime(twelveHourFormat: Int): Int {
            return when (twelveHourFormat) {
                23, 24, 0 -> 1
                1, 2 -> 2
                3, 4 -> 3
                5, 6 -> 4
                7, 8 -> 5
                9, 10 -> 6
                11, 12 -> 7
                13, 14 -> 8
                15, 16 -> 9
                17, 18 -> 10
                19, 20 -> 11
                21, 22 -> 12
                else -> 0
            }
        }

        /**
         * 计算农历年份的值
         */
        fun finalValueOfTheYear(year: String): Int {
            val parts = year.split("-")
            val parts2 = parts[1].split("(")
            val value = parts2[0]

            return when (value) {
                "zi" -> 1
                "chou" -> 2
                "yin" -> 3
                "mao" -> 4
                "chen" -> 5
                "si" -> 6
                "wu" -> 7
                "wei" -> 8
                "shen" -> 9
                "you" -> 10
                "xu" -> 11
                "hai" -> 12
                else -> 0
            }
        }

        /**
         * 求上卦
         */
        fun shanggua(year: Int, month: Int, day: Int): String {
            val shang = (year + month + day) % 8
            return when (shang) {
                1 -> "乾"
                2 -> "兑"
                3 -> "离"
                4 -> "震"
                5 -> "巽"
                6 -> "坎"
                7 -> "艮"
                0 -> "坤"
                else -> ""
            }
        }

        /**
         * 求下卦
         */
        fun xiagua(year: Int, month: Int, day: Int, time: Int): String {
            val xia = (year + month + day + time) % 8

            return when (xia) {
                1 -> "乾"
                2 -> "兑"
                3 -> "离"
                4 -> "震"
                5 -> "巽"
                6 -> "坎"
                7 -> "艮"
                0 -> "坤"
                else -> ""
            }
        }

        /**
         * 判断具体卦名
         */
        fun judgeGua(hu6: Boolean, hu5: Boolean, hu4: Boolean): String {
            var text = ""
            if (hu6 == true && hu5 == true && hu4 == true) {
                text = "乾(金)"
            }
            if (!hu6 == true && hu5 == true && hu4 == true) {
                text = "兑(金)"
            }
            if (hu6 == true && !hu5 == true && hu4 == true) {
                text = "离(火)"
            }
            if (!hu6 == true && !hu5 == true && hu4 == true) {
                text = "震(木)"
            }
            if (hu6 == true && hu5 == true && !hu4 == true) {
                text = "巽(木)"
            }
            if (!hu6 == true && hu5 == true && !hu4 == true) {
                text = "坎(水)"
            }
            if (hu6 == true && !hu5 == true && !hu4 == true) {
                text = "艮(土)"
            }
            if (!hu6 == true && !hu5 == true && !hu4 == true) {
                text = "坤(土)"
            }
            return text
        }

        /**
         * 就算时辰数
         */
        @RequiresApi(Build.VERSION_CODES.O)
        fun calcHour(): Int {
            val now = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("hh")
            val twelveHourFormat = now.format(formatter).toInt()
            val time = MeiHuaTools.conversionTime(twelveHourFormat) // 时辰数
            return time
        }

        /**
         * 物数起卦法计算上卦数
         */
        fun calcShangGua(number: Int):Int {
            var num = number % 8
            return num
        }

        /**
         * 根据数字求卦
         */
        fun qiugua(number: Int): String {
            return when (number) {
                1 -> "乾"
                2 -> "兑"
                3 -> "离"
                4 -> "震"
                5 -> "巽"
                6 -> "坎"
                7 -> "艮"
                0 -> "坤"
                else -> ""
            }
        }
    }
}