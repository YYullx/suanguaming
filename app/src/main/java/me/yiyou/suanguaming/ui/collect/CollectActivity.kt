package me.yiyou.suanguaming.ui.collect

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import com.kongzue.dialogx.dialogs.PopTip
import me.yiyou.suanguaming.MyApplication
import me.yiyou.suanguaming.R
import me.yiyou.suanguaming.Tiku
import me.yiyou.suanguaming.databinding.ActivityCollectBinding
import me.yiyou.suanguaming.ui.name.MainViewModel
import me.yiyou.suanguaming.ui.name.MainViewModelFactory
import kotlin.random.Random

class CollectActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCollectBinding
    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as MyApplication).repository)
    }
    private var guaName = ""
    private var explain = ""
    private var name = ""
    private var tiku: Tiku = Tiku(0, "", "", "", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCollectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        generateGua()

        binding.name.addTextChangedListener {
            name = it.toString()
        }
        // 提交
        binding.submit.setOnClickListener {
            if (!name.isNullOrEmpty()) {
                if (name == guaName) {
                    Toast.makeText(this, "恭喜你,答对了！", Toast.LENGTH_SHORT).show()
                    binding.explain.text = explain
                    generateGua()
                    binding.name.setText("")
                } else {
                    Toast.makeText(this, "很遗憾,回答错误,请重试！", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "请输入答案!", Toast.LENGTH_SHORT).show()
                Log.e("TAG", "错误,输入卦名:" + name)
                Log.e("TAG", "错误,json卦名:" + guaName)
            }
        }
        // 下一题
        binding.next.setOnClickListener {
            generateGua()
            binding.name.setText("")
            binding.explain.text = ""
        }
        // 提示
        binding.notice.setOnClickListener {
            binding.explain.text = explain
        }
        // 删除
        binding.icDelete.setOnClickListener{
            viewModel.delete(tiku)
            PopTip.show("删除成功!");
        }
    }

    private fun generateGua() {
        viewModel.allTiku.observe(this) { data ->
            if (data.isNotEmpty()){
                val randomNumber = Random.nextInt(data.size)
                tiku = data[randomNumber]

                binding.shanggua.text = tiku.shang
                binding.xiagua.text = tiku.xia
                guaName = tiku.ming
                explain = tiku.explain
                binding.shangImage.isVisible = true
                binding.xiaImage.isVisible = true
                binding.shangImage.setImageResource(guaImage(tiku.shang))
                binding.xiaImage.setImageResource(guaImage(tiku.xia))
            }else{
                binding.collectMain.isVisible = false
                binding.nullText.isVisible = true
            }
        }
    }

    private fun guaImage(shang: String): Int {
        when (shang) {
            "乾" -> return R.mipmap.qian
            "坎" -> return R.mipmap.kan
            "震" -> return R.mipmap.zhen
            "艮" -> return R.mipmap.gen
            "巽" -> return R.mipmap.xun
            "坤" -> return R.mipmap.kun
            "兑" -> return R.mipmap.dui
            "离" -> return R.mipmap.li
        }
        return 0
    }

}