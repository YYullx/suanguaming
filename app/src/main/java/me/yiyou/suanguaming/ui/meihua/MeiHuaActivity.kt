package me.yiyou.suanguaming.ui.meihua

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import me.yiyou.suanguaming.R
import me.yiyou.suanguaming.databinding.ActivityMeiHuaBinding
import net.time4j.calendar.ChineseCalendar
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MeiHuaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMeiHuaBinding
    private var dongyao = 0
    var huShangGua = ""
    var huXiaGua = ""

    var bianShangGua = ""
    var bianXiaGua = ""

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

        val time = MeiHuaTools.conversionTime(twelveHourFormat) // 时辰数
        val yearFinalValue = MeiHuaTools.finalValueOfTheYear(year)

        qigua(yearFinalValue, month, day, time) //起卦
    }


    /**
     * 根据年月日时起卦
     */
    private fun qigua(year: Int, month: Int, day: Int, time: Int) {
//        println("年:$year 月:$month 日:$day 时:$time")
        val shanggua = MeiHuaTools.shanggua(year, month, day)
        val xiagua = MeiHuaTools.xiagua(year, month, day, time)
        dongyao = (year + month + day + time) % 6   // 求动爻
        rendingGua(shanggua, xiagua)  // 渲染ui,画卦
    }

    /**
     * 渲染ui,画卦
     */
    private fun rendingGua(shanggua: String, xiagua: String) {
        var liu = true
        var wu = true
        var si = true
        var san = true
        var er = true
        var yi = true

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
                liu = false
            }

            "离" -> {
                binding.liuyao.setImageResource(R.mipmap.yang)
                binding.wuyao.setImageResource(R.mipmap.yin)
                binding.siyao.setImageResource(R.mipmap.yang)
                wu = false
            }

            "震" -> {
                binding.liuyao.setImageResource(R.mipmap.yin)
                binding.wuyao.setImageResource(R.mipmap.yin)
                binding.siyao.setImageResource(R.mipmap.yang)
                liu = false
                wu = false
            }

            "巽" -> {
                binding.liuyao.setImageResource(R.mipmap.yang)
                binding.wuyao.setImageResource(R.mipmap.yang)
                binding.siyao.setImageResource(R.mipmap.yin)
                si = false
            }

            "坎" -> {
                binding.liuyao.setImageResource(R.mipmap.yin)
                binding.wuyao.setImageResource(R.mipmap.yang)
                binding.siyao.setImageResource(R.mipmap.yin)
                liu = false
                si = false
            }

            "艮" -> {
                binding.liuyao.setImageResource(R.mipmap.yang)
                binding.wuyao.setImageResource(R.mipmap.yin)
                binding.siyao.setImageResource(R.mipmap.yin)
                wu = false
                si = false
            }

            "坤" -> {
                binding.liuyao.setImageResource(R.mipmap.yin)
                binding.wuyao.setImageResource(R.mipmap.yin)
                binding.siyao.setImageResource(R.mipmap.yin)
                liu = false
                wu = false
                si = false
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
                san = false
            }

            "离" -> {
                binding.liuyao.setImageResource(R.mipmap.yang)
                binding.eryao.setImageResource(R.mipmap.yin)
                binding.chuyao.setImageResource(R.mipmap.yang)
                er = false
            }

            "震" -> {
                binding.sanyao.setImageResource(R.mipmap.yin)
                binding.eryao.setImageResource(R.mipmap.yin)
                binding.chuyao.setImageResource(R.mipmap.yang)
                san = false
                er = false
            }

            "巽" -> {
                binding.sanyao.setImageResource(R.mipmap.yang)
                binding.eryao.setImageResource(R.mipmap.yang)
                binding.chuyao.setImageResource(R.mipmap.yin)
                yi = false
            }

            "坎" -> {
                binding.sanyao.setImageResource(R.mipmap.yin)
                binding.eryao.setImageResource(R.mipmap.yang)
                binding.chuyao.setImageResource(R.mipmap.yin)
                san = false
                yi = false
            }

            "艮" -> {
                binding.sanyao.setImageResource(R.mipmap.yang)
                binding.eryao.setImageResource(R.mipmap.yin)
                binding.chuyao.setImageResource(R.mipmap.yin)
                er = false
                yi = false
            }

            "坤" -> {
                binding.sanyao.setImageResource(R.mipmap.yin)
                binding.eryao.setImageResource(R.mipmap.yin)
                binding.chuyao.setImageResource(R.mipmap.yin)
                san = false
                er = false
                yi = false
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

        val shang = MeiHuaTools.judgeGua(liu, wu, si)
        val xia = MeiHuaTools.judgeGua(san, er, yi)
        binding.tvBengua.text = "本卦:$shang-$xia-$dongyao" + "爻动"
        huaBianGua(shanggua, xiagua, dongyao)   // 变卦
        huaHuGua(shanggua, xiagua)  // 互卦
    }

    /**
     * 互卦
     */
    private fun huaHuGua(shanggua1: String, xiagua1: String) {
        var hu6 = true
        var hu5 = true
        var hu4 = true
        var hu3 = true
        var hu2 = true
        var hu1 = true
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
                hu6 = false
            }

            "震" -> {
                binding.huliu.setImageResource(R.mipmap.yin)
                binding.huwu.setImageResource(R.mipmap.yang)
                binding.husan.setImageResource(R.mipmap.yang)
                hu6 = false
            }

            "巽" -> {
                binding.huliu.setImageResource(R.mipmap.yang)
                binding.huwu.setImageResource(R.mipmap.yin)
                binding.husan.setImageResource(R.mipmap.yin)
                hu5 = false
                hu3 = false
            }

            "坎" -> {
                binding.huliu.setImageResource(R.mipmap.yang)
                binding.huwu.setImageResource(R.mipmap.yin)
                binding.husan.setImageResource(R.mipmap.yin)
                hu5 = false
                hu3 = false
            }

            "艮" -> {
                binding.huliu.setImageResource(R.mipmap.yin)
                binding.huwu.setImageResource(R.mipmap.yin)
                binding.husan.setImageResource(R.mipmap.yin)
                hu6 = false
                hu5 = false
                hu3 = false
            }

            "坤" -> {
                binding.huliu.setImageResource(R.mipmap.yin)
                binding.huwu.setImageResource(R.mipmap.yin)
                binding.husan.setImageResource(R.mipmap.yin)
                hu6 = false
                hu5 = false
                hu3 = false
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
                binding.huer.setImageResource(R.mipmap.yin)
                binding.huyi.setImageResource(R.mipmap.yang)
                hu4 = false
                hu2 = false
            }

            "离" -> {
                binding.husi.setImageResource(R.mipmap.yin)
                binding.huer.setImageResource(R.mipmap.yang)
                binding.huyi.setImageResource(R.mipmap.yin)
                hu4 = false
                hu1 = false
            }

            "震" -> {
                binding.husi.setImageResource(R.mipmap.yin)
                binding.huer.setImageResource(R.mipmap.yin)
                binding.huyi.setImageResource(R.mipmap.yin)
                hu4 = false
                hu2 = false
                hu1 = false
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
                hu4 = false
                hu2 = false
            }

            "艮" -> {
                binding.husi.setImageResource(R.mipmap.yang)
                binding.huer.setImageResource(R.mipmap.yang)
                binding.huyi.setImageResource(R.mipmap.yin)
                hu1 = false
            }

            "坤" -> {
                binding.husi.setImageResource(R.mipmap.yin)
                binding.huer.setImageResource(R.mipmap.yin)
                binding.huyi.setImageResource(R.mipmap.yin)
                hu4 = false
                hu2 = false
                hu1 = false
            }
        }

        huShangGua = MeiHuaTools.judgeGua(hu6, hu5, hu4)   // 判断互卦上下卦
        huXiaGua = MeiHuaTools.judgeGua(hu3, hu2, hu1)   // 判断互卦上下卦
        binding.tvHugua.text = "互卦:$huShangGua-$huXiaGua"
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
                er = false
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
                yi = false
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
                    liu = false
                } else {
                    binding.bianliu.setImageResource(R.mipmap.yang)
                    liu = true
                }
            }

            1 -> {
                if (yi) {
                    binding.bianyi.setImageResource(R.mipmap.yin)
                    yi = false
                } else {
                    binding.bianyi.setImageResource(R.mipmap.yang)
                    yi = true
                }
            }

            2 -> {
                if (er) {
                    binding.bianer.setImageResource(R.mipmap.yin)
                    er = false
                } else {
                    binding.bianer.setImageResource(R.mipmap.yang)
                    er = true
                }
            }

            3 -> {
                if (san) {
                    binding.biansan.setImageResource(R.mipmap.yin)
                    san = false
                } else {
                    binding.biansan.setImageResource(R.mipmap.yang)
                    san = true
                }
            }

            4 -> {
                if (si) {
                    binding.biansi.setImageResource(R.mipmap.yin)
                    si = false
                } else {
                    binding.biansi.setImageResource(R.mipmap.yang)
                    si = true
                }
            }

            5 -> {
                if (wu) {
                    binding.bianwu.setImageResource(R.mipmap.yin)
                    wu = false
                } else {
                    binding.bianwu.setImageResource(R.mipmap.yang)
                    wu = true
                }
            }
        }
        bianShangGua = MeiHuaTools.judgeGua(liu, wu, si)
        bianXiaGua = MeiHuaTools.judgeGua(san, er, yi)
        binding.tvBiangua.text = "变卦:$bianShangGua-$bianXiaGua"
    }

}