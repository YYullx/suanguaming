package me.yiyou.suanguaming.ui.meihua

import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import com.kongzue.dialogx.dialogs.PopTip
import me.yiyou.suanguaming.MyApplication
import me.yiyou.suanguaming.R
import me.yiyou.suanguaming.databinding.ActivityNumberBinding
import java.time.LocalDateTime


class NumberActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNumberBinding
    var number = 0
    var inputNumber = 0
    var etInput = ""
    var hourNumber = 0
    private val viewModel: MeiHuaViewModel by viewModels {
        MeiHuaViewModelFactory((application as MyApplication).repositoryMeiHua)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNumberBinding.inflate(layoutInflater)

        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            initView()
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initView() {
        binding.editNumber.addTextChangedListener {
            val input: String = it.toString().trim() // 去除前后空白
            if (!TextUtils.isEmpty(input)) {
                try {
                    inputNumber = input.toInt()
                } catch (e: NumberFormatException) {
                    PopTip.show("请输入大于0的数字!")
                    e.printStackTrace()
                }
            }
        }
        hourNumber = MeiHuaTools.calcHour() // 获取当前时辰数

        binding.generated.setOnClickListener {
            etInput = binding.etInput.text.toString()
            if(!etInput.isNotEmpty()){
                PopTip.show("请输入占问事!")
                return@setOnClickListener
            }
            if (inputNumber > 0) {
                number = MeiHuaTools.calcShangGua(inputNumber)  //  计算输入数字得出的上卦
                binding.linerEditNumber.isVisible = false
                binding.linerGuaUi.isVisible = true
                binding.linerExplain.isVisible = true
                qigua(number, hourNumber)
                val nowtime = LocalDateTime.now()     // 当前时间

                val data =
                    MeiHuaBean(0, null, null, null, null, nowtime.toString(), etInput, 2, number, hourNumber, null, null)
                viewModel.insert(data)
            } else {
                PopTip.show("请输入大于0的数字!");
            }
        }
    }

    /**
     * 本卦
     */
    private fun qigua(number: Int, hourNumber: Int) {
        val shanggua = MeiHuaTools.qiugua(number)
        val xiagua = MeiHuaTools.qiugua((number + hourNumber) % 8)
        val dongyao = (number + hourNumber) % 6
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
}