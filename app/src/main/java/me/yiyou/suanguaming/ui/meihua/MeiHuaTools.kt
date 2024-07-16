package me.yiyou.suanguaming.ui.meihua

import me.yiyou.suanguaming.R

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
            if (hu6 && hu5 && hu4) {
                text = "乾"
            }
            if (!hu6 && hu5 && hu4) {
                text = "兑"
            }
            if (hu6 && !hu5 && hu4) {
                text = "离"
            }
            if (!hu6 && !hu5 && hu4) {
                text = "震"
            }
            if (hu6 && hu5 && !hu4) {
                text = "巽"
            }
            if (!hu6 && hu5 && !hu4) {
                text = "坎"
            }
            if (!hu6 && !hu5 && hu4) {
                text = "艮"
            }
            if (!hu6 && !hu5 && !hu4) {
                text = "坤"
            }
            return text
        }

    }
}