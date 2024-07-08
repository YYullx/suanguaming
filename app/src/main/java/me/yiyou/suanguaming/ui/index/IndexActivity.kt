package me.yiyou.suanguaming.ui.index

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import me.yiyou.suanguaming.R
import me.yiyou.suanguaming.databinding.ActivityIndexBinding
import me.yiyou.suanguaming.ui.collect.CollectActivity
import me.yiyou.suanguaming.ui.meihua.MeiHuaActivity
import me.yiyou.suanguaming.ui.name.MainActivity

class IndexActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIndexBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIndexBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.guaming.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.collect.setOnClickListener{
            startActivity(Intent(this, CollectActivity::class.java))
        }

        binding.meihuagua.setOnClickListener{
            startActivity(Intent(this, MeiHuaActivity::class.java))
        }
    }
}