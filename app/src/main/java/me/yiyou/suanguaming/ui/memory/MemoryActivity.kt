package me.yiyou.suanguaming.ui.memory

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import me.yiyou.suanguaming.Gua
import me.yiyou.suanguaming.R
import me.yiyou.suanguaming.databinding.ActivityMemoryBinding
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class MemoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMemoryBinding
    private lateinit var viewModel: MemoryViewModel

    var nextNumber = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMemoryBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(MemoryViewModel::class.java)
        setContentView(binding.root)

        generateGua(0)

        binding.next.setOnClickListener {
            if (nextNumber < 63) {
                nextNumber++

//                // 观察ViewModel中的number LiveData
//                viewModel.updateNumber(nextNumber)
//                viewModel.number.observe(this, Observer { number ->
//                    generateGua(number)
//                })
                generateGua(nextNumber)
            } else {
                nextNumber = 0
                generateGua(nextNumber)
            }
        }

        binding.last.setOnClickListener {
            if (nextNumber > 0 && nextNumber < 64) {
                nextNumber--
                generateGua(nextNumber)
            }
        }
    }

    /**
     * 生成卦象
     */
    private fun generateGua(number: Int) {
        // 异步读取assets中的JSON文件
        val gson = Gson()
        val thread = Thread {
            try {
                val inputStream = assets.open("gua.json")
                val inputStreamReader = InputStreamReader(inputStream, Charsets.UTF_8)
                val reader = BufferedReader(inputStreamReader)

                val jsonString = reader.use { it.readText() }
                val gua = gson.fromJson(jsonString, Gua::class.java)

                runOnUiThread {
                    binding.shanggua.text = gua.gua[number].shang
                    binding.xiagua.text = gua.gua[number].xia
                    binding.shangImage.setImageResource(guaImage(gua.gua[number].shang))
                    binding.xiaImage.setImageResource(guaImage(gua.gua[number].xia))
                    binding.guaming.text = gua.gua[number].ming
                    binding.explain.text = gua.gua[number].explain
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