package me.yiyou.suanguaming.ui.meihua

import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.kongzue.dialogx.dialogs.PopTip
import me.yiyou.suanguaming.MyApplication
import me.yiyou.suanguaming.R
import me.yiyou.suanguaming.databinding.ActivityRandomBinding
import java.time.LocalDateTime
import kotlin.random.Random

class RandomActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRandomBinding
    private val viewModel: MeiHuaViewModel by viewModels {
        MeiHuaViewModelFactory((application as MyApplication).repositoryMeiHua)
    }
    var shangNumber = 0
    var xiaNumber = 0
    var shang = 0
    var xia = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRandomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            initView()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initView() {
        binding.randomShang.setOnClickListener {
            shangNumber = rendomNumber()
//            println("shangNumber:$shangNumber")
            binding.randomShangText.isVisible = true
            binding.randomShangText.text = shangNumber.toString()
            shang = shangNumber % 8

        }
        binding.randomXia.setOnClickListener {
            xiaNumber = rendomNumber()
//            println("xiaNumber:$xiaNumber")
            binding.randomXiaText.isVisible = true
            binding.randomXiaText.text = xiaNumber.toString()
            xia = xiaNumber % 8
        }
        binding.randomSubmit.setOnClickListener {
            val nowtime = LocalDateTime.now()     // 当前时间
            if (shangNumber != 0 && xiaNumber != 0) {
                getGua(shang, xia)
                val data = MeiHuaBean(0, null, null, null, null, nowtime.toString(), null, 3, null, null, shangNumber, xiaNumber)
                viewModel.insert(data)
                binding.randomLayout.isVisible = false
                binding.randomGua.isVisible = true
                binding.randomExplain.isVisible = true
            } else {
                PopTip.show("请先生成卦数")
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getGua(shangnumber: Int, xianumber: Int) {
        val shanggua = MeiHuaTools.calcShangGua(shangnumber)  //  计算输入数字得出的上卦
        val xiagua = MeiHuaTools.calcShangGua(xianumber)  //  计算输入数字得出的下卦
        val hourNumber = MeiHuaTools.calcHour() // 获取当前时辰数
        qigua(shanggua, xiagua, hourNumber)
    }

    private fun qigua(shang: Int, xia: Int, hourNumber: Int) {
        val shanggua = MeiHuaTools.qiugua(shang)
        val xiagua = MeiHuaTools.qiugua(xia)
        val dongyao = (shang + xia + hourNumber) % 6
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

        val huShangGua = MeiHuaTools.judgeGua(hu6, hu5, hu4)   // 判断互卦上下卦
        val huXiaGua = MeiHuaTools.judgeGua(hu3, hu2, hu1)   // 判断互卦上下卦
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
        val bianShangGua = MeiHuaTools.judgeGua(liu, wu, si)
        val bianXiaGua = MeiHuaTools.judgeGua(san, er, yi)
        binding.tvBiangua.text = "变卦:$bianShangGua-$bianXiaGua"
    }

    fun rendomNumber(): Int {
        val randomNumber = Random.nextInt(1, 101)
        return randomNumber
    }
}