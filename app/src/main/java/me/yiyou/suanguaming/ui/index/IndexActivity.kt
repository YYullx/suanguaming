package me.yiyou.suanguaming.ui.index

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.yiyou.suanguaming.databinding.ActivityIndexBinding
import me.yiyou.suanguaming.ui.collect.CollectActivity
import me.yiyou.suanguaming.ui.meihua.MeiHuaActivity
import me.yiyou.suanguaming.ui.memory.MemoryActivity
import me.yiyou.suanguaming.ui.name.MainActivity
import me.yiyou.suanguaming.ui.setting.SettingActivity

class IndexActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIndexBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIndexBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.setting.setOnClickListener {
            startActivity(Intent(this, SettingActivity::class.java))
        }

        binding.guaming.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.collect.setOnClickListener {
            val intent = Intent(this, CollectActivity::class.java)
            startActivity(intent)
        }

        binding.meihuagua.setOnClickListener {
            val intent = Intent(this, MeiHuaActivity::class.java)
            startActivity(intent)
        }

        binding.sequentialMemory.setOnClickListener {
            startActivity(Intent(this, MemoryActivity::class.java))
        }
    }
}