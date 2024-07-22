package me.yiyou.suanguaming.ui.index

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.yiyou.suanguaming.databinding.ActivityIndexBinding
import me.yiyou.suanguaming.ui.collect.CollectActivity
import me.yiyou.suanguaming.ui.meihua.MeiCollectActivity
import me.yiyou.suanguaming.ui.meihua.MeiHuaActivity
import me.yiyou.suanguaming.ui.meihua.NumberActivity
import me.yiyou.suanguaming.ui.meihua.RandomActivity
import me.yiyou.suanguaming.ui.memory.MemoryActivity
import me.yiyou.suanguaming.ui.name.MainActivity
import me.yiyou.suanguaming.ui.setting.SettingActivity

class IndexActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIndexBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIndexBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 设置页
        binding.setting.setOnClickListener {
            startActivity(Intent(this, SettingActivity::class.java))
        }

        // 背卦名
        binding.guaming.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // 收藏
        binding.collect.setOnClickListener {
            val intent = Intent(this, CollectActivity::class.java)
            startActivity(intent)
        }

        // 年月日时起卦
        binding.meihuagua.setOnClickListener {
            val intent = Intent(this, MeiHuaActivity::class.java)
            startActivity(intent)
        }

        // 顺序记忆
        binding.sequentialMemory.setOnClickListener {
            startActivity(Intent(this, MemoryActivity::class.java))
        }

        // 数字起卦
        binding.numberQigua.setOnClickListener {
            startActivity(Intent(this, NumberActivity::class.java))
        }

        // 随机起卦
        binding.randomGua.setOnClickListener {
            startActivity(Intent(this, RandomActivity::class.java))
        }

        // 梅花起卦记录
        binding.gotomeihuacollect.setOnClickListener{
            startActivity(Intent(this, MeiCollectActivity::class.java))
        }
    }
}