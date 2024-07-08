package me.yiyou.suanguaming.ui.meihua

import android.icu.lang.UCharacter.EastAsianWidth
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import kotlinx.coroutines.android.awaitFrame
import me.yiyou.suanguaming.R
import me.yiyou.suanguaming.databinding.ActivityMeiHuaBinding
import net.time4j.calendar.ChineseCalendar
import net.time4j.calendar.EastAsianMonth
import net.time4j.calendar.EastAsianYear
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MeiHuaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMeiHuaBinding
    private var dongyao = 0

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMeiHuaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val date = ChineseCalendar.nowInSystemTime()
        val year = date.year.toString() // 农历年
        val month = date.month.number   // 农历月
        val day = date.dayOfMonth   // 农历日

        val now = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("hh")
        val twelveHourFormat = now.format(formatter).toInt()

        val time = conversionTime(twelveHourFormat) // 时辰数
        val yearFinalValue = finalValueOfTheYear(year)

        qigua(yearFinalValue, month, day, time) //起卦
    }

    /**
     * 计算农历年份的值
     */
    private fun finalValueOfTheYear(year: String): Int {
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
     * 获取当前的时辰数
     */
    private fun conversionTime(twelveHourFormat: Int): Int {
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
     * 根据年月日时起卦
     */
    private fun qigua(year: Int, month: Int, day: Int, time: Int) {
        println("年:$year 月:$month 日:$day 时:$time")
        val shanggua = shanggua(year, month, day)
        val xiagua = xiagua(year, month, day, time)

        huagua(shanggua, xiagua)  // 渲染ui,画卦

    }

    /**
     * 渲染ui,画卦
     */
    private fun huagua(shanggua: String, xiagua: String) {

        when (shanggua) {
            "乾" -> {
                binding.liuyao.setImageResource(R.mipmap.yang)
                binding.wuyao.setImageResource(R.mipmap.yang)
                binding.siyao.setImageResource(R.mipmap.yang)
            }

            "兑" -> {
                binding.liuyao.setImageResource(R.mipmap.yin)
                binding.wuyao.setImageResource(R.mipmap.yang)
                binding.siyao.setImageResource(R.mipmap.yang)
            }

            "离" -> {
                binding.liuyao.setImageResource(R.mipmap.yang)
                binding.wuyao.setImageResource(R.mipmap.yin)
                binding.siyao.setImageResource(R.mipmap.yang)
            }

            "震" -> {
                binding.liuyao.setImageResource(R.mipmap.yin)
                binding.wuyao.setImageResource(R.mipmap.yin)
                binding.siyao.setImageResource(R.mipmap.yang)
            }

            "巽" -> {
                binding.liuyao.setImageResource(R.mipmap.yang)
                binding.wuyao.setImageResource(R.mipmap.yang)
                binding.siyao.setImageResource(R.mipmap.yin)
            }

            "坎" -> {
                binding.liuyao.setImageResource(R.mipmap.yin)
                binding.wuyao.setImageResource(R.mipmap.yang)
                binding.siyao.setImageResource(R.mipmap.yin)
            }

            "艮" -> {
                binding.liuyao.setImageResource(R.mipmap.yang)
                binding.wuyao.setImageResource(R.mipmap.yin)
                binding.siyao.setImageResource(R.mipmap.yin)
            }

            "坤" -> {
                binding.liuyao.setImageResource(R.mipmap.yin)
                binding.wuyao.setImageResource(R.mipmap.yin)
                binding.siyao.setImageResource(R.mipmap.yin)
            }
        }

        when (xiagua) {
            "乾" -> {
                binding.sanyao.setImageResource(R.mipmap.yang)
                binding.eryao.setImageResource(R.mipmap.yang)
                binding.chuyao.setImageResource(R.mipmap.yang)
            }

            "兑" -> {
                binding.sanyao.setImageResource(R.mipmap.yin)
                binding.eryao.setImageResource(R.mipmap.yang)
                binding.chuyao.setImageResource(R.mipmap.yang)
            }

            "离" -> {
                binding.liuyao.setImageResource(R.mipmap.yang)
                binding.eryao.setImageResource(R.mipmap.yin)
                binding.chuyao.setImageResource(R.mipmap.yang)
            }

            "震" -> {
                binding.sanyao.setImageResource(R.mipmap.yin)
                binding.eryao.setImageResource(R.mipmap.yin)
                binding.chuyao.setImageResource(R.mipmap.yang)
            }

            "巽" -> {
                binding.sanyao.setImageResource(R.mipmap.yang)
                binding.eryao.setImageResource(R.mipmap.yang)
                binding.chuyao.setImageResource(R.mipmap.yin)
            }

            "坎" -> {
                binding.sanyao.setImageResource(R.mipmap.yin)
                binding.eryao.setImageResource(R.mipmap.yang)
                binding.chuyao.setImageResource(R.mipmap.yin)
            }

            "艮" -> {
                binding.sanyao.setImageResource(R.mipmap.yang)
                binding.eryao.setImageResource(R.mipmap.yin)
                binding.chuyao.setImageResource(R.mipmap.yin)
            }

            "坤" -> {
                binding.sanyao.setImageResource(R.mipmap.yin)
                binding.eryao.setImageResource(R.mipmap.yin)
                binding.chuyao.setImageResource(R.mipmap.yin)
            }
        }

        when (dongyao) {
            0 -> {
                binding.dongliu.text = "○"
            }

            1 -> {
                binding.dongyi.text = "○"
            }

            2 -> {
                binding.donger.text = "○"
            }

            3 -> {
                binding.dongsan.text = "○"
            }

            4 -> {
                binding.dongsi.text = "○"
            }

            5 -> {
                binding.dongwu.text = "○"
            }
        }

        binding.tvBengua.text = "本卦:$shanggua-$xiagua-$dongyao" + "爻动"
        huaBianGua(shanggua, xiagua, dongyao)   // 变卦
        huaHuGua(shanggua, xiagua)  // 互卦
    }

    /**
     * 互卦
     */
    private fun huaHuGua(shanggua1: String, xiagua1: String) {
        when (shanggua1) {
            "乾" -> {
                binding.huliu.setImageResource(R.mipmap.yang)
                binding.huwu.setImageResource(R.mipmap.yang)
                binding.husan.setImageResource(R.mipmap.yang)
            }

            "兑" -> {
                binding.huliu.setImageResource(R.mipmap.yang)
                binding.huwu.setImageResource(R.mipmap.yang)
                binding.husan.setImageResource(R.mipmap.yang)
            }

            "离" -> {
                binding.huliu.setImageResource(R.mipmap.yin)
                binding.huwu.setImageResource(R.mipmap.yang)
                binding.husan.setImageResource(R.mipmap.yang)
            }

            "震" -> {
                binding.huliu.setImageResource(R.mipmap.yin)
                binding.huwu.setImageResource(R.mipmap.yang)
                binding.husan.setImageResource(R.mipmap.yang)
            }

            "巽" -> {
                binding.huliu.setImageResource(R.mipmap.yang)
                binding.huwu.setImageResource(R.mipmap.yin)
                binding.husan.setImageResource(R.mipmap.yin)
            }

            "坎" -> {
                binding.huliu.setImageResource(R.mipmap.yang)
                binding.huwu.setImageResource(R.mipmap.yin)
                binding.husan.setImageResource(R.mipmap.yin)
            }

            "艮" -> {
                binding.huliu.setImageResource(R.mipmap.yin)
                binding.huwu.setImageResource(R.mipmap.yin)
                binding.husan.setImageResource(R.mipmap.yin)
            }

            "坤" -> {
                binding.huliu.setImageResource(R.mipmap.yin)
                binding.huwu.setImageResource(R.mipmap.yin)
                binding.husan.setImageResource(R.mipmap.yin)
            }
        }
        when (xiagua1) {
            "乾" -> {
                binding.husi.setImageResource(R.mipmap.yang)
                binding.huer.setImageResource(R.mipmap.yang)
                binding.huyi.setImageResource(R.mipmap.yang)
            }

            "兑" -> {
                binding.husi.setImageResource(R.mipmap.yin)
                binding.husan.setImageResource(R.mipmap.yin)
                binding.huer.setImageResource(R.mipmap.yang)
            }

            "离" -> {
                binding.husi.setImageResource(R.mipmap.yin)
                binding.huer.setImageResource(R.mipmap.yang)
                binding.huyi.setImageResource(R.mipmap.yin)
            }

            "震" -> {
                binding.husi.setImageResource(R.mipmap.yin)
                binding.huer.setImageResource(R.mipmap.yin)
                binding.huyi.setImageResource(R.mipmap.yin)
            }

            "巽" -> {
                binding.husi.setImageResource(R.mipmap.yang)
                binding.huer.setImageResource(R.mipmap.yang)
                binding.huyi.setImageResource(R.mipmap.yang)
            }

            "坎" -> {
                binding.husi.setImageResource(R.mipmap.yin)
                binding.huer.setImageResource(R.mipmap.yin)
                binding.huyi.setImageResource(R.mipmap.yang)
            }

            "艮" -> {
                binding.husi.setImageResource(R.mipmap.yang)
                binding.huer.setImageResource(R.mipmap.yang)
                binding.huyi.setImageResource(R.mipmap.yin)
            }

            "坤" -> {
                binding.husi.setImageResource(R.mipmap.yin)
                binding.huer.setImageResource(R.mipmap.yin)
                binding.huyi.setImageResource(R.mipmap.yin)
            }
        }
    }

    /**
     * 变卦
     */
    private fun huaBianGua(shanggua: String, xiagua: String, dongyao: Int) {
        var liu = true
        var wu = true
        var si = true
        var san = true
        var er = true
        var yi = true

        when (shanggua) {
            "乾" -> {
                binding.bianliu.setImageResource(R.mipmap.yang)
                binding.bianwu.setImageResource(R.mipmap.yang)
                binding.biansi.setImageResource(R.mipmap.yang)
            }

            "兑" -> {
                binding.bianliu.setImageResource(R.mipmap.yin)
                binding.bianwu.setImageResource(R.mipmap.yang)
                binding.biansi.setImageResource(R.mipmap.yang)
                liu = false
            }

            "离" -> {
                binding.bianliu.setImageResource(R.mipmap.yang)
                binding.bianwu.setImageResource(R.mipmap.yin)
                binding.biansi.setImageResource(R.mipmap.yang)
                wu = false
            }

            "震" -> {
                binding.bianliu.setImageResource(R.mipmap.yin)
                binding.bianwu.setImageResource(R.mipmap.yin)
                binding.biansi.setImageResource(R.mipmap.yang)
                liu = false
                wu = false
            }

            "巽" -> {
                binding.bianliu.setImageResource(R.mipmap.yang)
                binding.bianwu.setImageResource(R.mipmap.yang)
                binding.biansi.setImageResource(R.mipmap.yin)
                si = false
            }

            "坎" -> {
                binding.bianliu.setImageResource(R.mipmap.yin)
                binding.bianwu.setImageResource(R.mipmap.yang)
                binding.biansi.setImageResource(R.mipmap.yin)
                liu = false
                si = false
            }

            "艮" -> {
                binding.bianliu.setImageResource(R.mipmap.yang)
                binding.bianwu.setImageResource(R.mipmap.yin)
                binding.biansi.setImageResource(R.mipmap.yin)
                wu = false
                si = false
            }

            "坤" -> {
                binding.bianliu.setImageResource(R.mipmap.yin)
                binding.bianwu.setImageResource(R.mipmap.yin)
                binding.biansi.setImageResource(R.mipmap.yin)
                liu = false
                wu = false
                si = false
            }
        }

        when (xiagua) {
            "乾" -> {
                binding.biansan.setImageResource(R.mipmap.yang)
                binding.bianer.setImageResource(R.mipmap.yang)
                binding.bianyi.setImageResource(R.mipmap.yang)
            }

            "兑" -> {
                binding.biansan.setImageResource(R.mipmap.yin)
                binding.bianer.setImageResource(R.mipmap.yang)
                binding.bianyi.setImageResource(R.mipmap.yang)
                san = false
            }

            "离" -> {
                binding.biansan.setImageResource(R.mipmap.yang)
                binding.bianer.setImageResource(R.mipmap.yin)
                binding.bianyi.setImageResource(R.mipmap.yang)
                er  = false
            }

            "震" -> {
                binding.biansan.setImageResource(R.mipmap.yin)
                binding.bianer.setImageResource(R.mipmap.yin)
                binding.bianyi.setImageResource(R.mipmap.yang)
                san = false
                er = false
            }

            "巽" -> {
                binding.biansan.setImageResource(R.mipmap.yang)
                binding.bianer.setImageResource(R.mipmap.yang)
                binding.bianyi.setImageResource(R.mipmap.yin)
            }

            "坎" -> {
                binding.biansan.setImageResource(R.mipmap.yin)
                binding.bianer.setImageResource(R.mipmap.yang)
                binding.bianyi.setImageResource(R.mipmap.yin)
                san = false
                yi = false
            }

            "艮" -> {
                binding.biansan.setImageResource(R.mipmap.yang)
                binding.bianer.setImageResource(R.mipmap.yin)
                binding.bianyi.setImageResource(R.mipmap.yin)
                er = false
                yi = false
            }

            "坤" -> {
                binding.biansan.setImageResource(R.mipmap.yin)
                binding.bianer.setImageResource(R.mipmap.yin)
                binding.bianyi.setImageResource(R.mipmap.yin)
                san = false
                er = false
                yi = false
            }
        }

        when (dongyao) {
            0 -> {
                if (liu) {
                    binding.bianliu.setImageResource(R.mipmap.yin)
                } else {
                    binding.bianliu.setImageResource(R.mipmap.yang)
                }
            }

            1 -> {
                if (yi) {
                    binding.bianyi.setImageResource(R.mipmap.yin)
                } else {
                    binding.bianyi.setImageResource(R.mipmap.yang)
                }
            }

            2 -> {
                if (er) {
                    binding.bianer.setImageResource(R.mipmap.yin)
                } else {
                    binding.bianer.setImageResource(R.mipmap.yang)
                }
            }

            3 -> {
                if (san) {
                    binding.biansan.setImageResource(R.mipmap.yin)
                } else {
                    binding.biansan.setImageResource(R.mipmap.yang)
                }
            }

            4 -> {
                if (si) {
                    binding.biansi.setImageResource(R.mipmap.yin)
                } else {
                    binding.biansi.setImageResource(R.mipmap.yang)
                }
            }

            5 -> {
                if (wu) {
                    binding.bianwu.setImageResource(R.mipmap.yin)
                } else {
                    binding.bianwu.setImageResource(R.mipmap.yang)
                }
            }
        }
    }


    /**
     * 求上卦
     */
    private fun shanggua(year: Int, month: Int, day: Int): String {
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
    private fun xiagua(year: Int, month: Int, day: Int, time: Int): String {
        val xia = (year + month + day + time) % 8
        dongyao = (year + month + day + time) % 6

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


}