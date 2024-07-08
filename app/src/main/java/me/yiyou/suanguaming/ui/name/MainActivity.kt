package me.yiyou.suanguaming.ui.name

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import com.google.gson.Gson
import com.kongzue.dialogx.dialogs.PopTip
import me.yiyou.suanguaming.Gua
import me.yiyou.suanguaming.MyApplication
import me.yiyou.suanguaming.R
import me.yiyou.suanguaming.Tiku
import me.yiyou.suanguaming.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var gua: Gua
    var name = ""   // 输入名
    var guaName = ""    // 卦名
    var explain = ""    // 卦义
    var data: Tiku = Tiku(0, "", "", "", "")

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as MyApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
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
        // 添加收藏
        binding.icAddcollect.setOnClickListener {
            viewModel.insert(data)
            PopTip.show("添加收藏成功!");
        }
    }

    /**
     * 生成卦象
     */
    private fun generateGua() {
        // 异步读取assets中的JSON文件
        val gson = Gson()
        val thread = Thread {
            try {
                val inputStream = assets.open("gua.json")
                val inputStreamReader = InputStreamReader(inputStream, Charsets.UTF_8)
                val reader = BufferedReader(inputStreamReader)

                val jsonString = reader.use { it.readText() }
                val randomNumber = Random.nextInt(64)  // 生成一个随机整数
                gua = gson.fromJson(jsonString, Gua::class.java)

                runOnUiThread {
                    binding.shanggua.text = gua.gua[randomNumber].shang
                    binding.xiagua.text = gua.gua[randomNumber].xia
                    guaName = gua.gua[randomNumber].ming
                    explain = gua.gua[randomNumber].explain
                    binding.shangImage.isVisible = true
                    binding.xiaImage.isVisible = true
                    binding.shangImage.setImageResource(guaImage(gua.gua[randomNumber].shang))
                    binding.xiaImage.setImageResource(guaImage(gua.gua[randomNumber].xia))

                    data = Tiku(
                        0,
                        gua.gua[randomNumber].ming,
                        gua.gua[randomNumber].shang,
                        gua.gua[randomNumber].xia,
                        gua.gua[randomNumber].explain
                    )
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        thread.start()
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