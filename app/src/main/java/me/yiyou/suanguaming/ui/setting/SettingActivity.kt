package me.yiyou.suanguaming.ui.setting

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tencent.mmkv.MMKV
import me.yiyou.suanguaming.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingBinding

    var mmkv = MMKV.defaultMMKV()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.datiSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            // 判断Switch的状态
            if (isChecked) {
                // Switch被打开时执行的操作
                mmkv.encode("autoAnswer", true)
                println("autoAnswer:true")
            } else {
                // Switch被关闭时执行的操作
                mmkv.encode("autoAnswer", false)
                println("autoAnswer:false")
            }
        }

        if (mmkv.decodeBool("autoAnswer", false)){
            binding.datiSwitch.isChecked = true
        }else{
            binding.datiSwitch.isChecked = false
        }
    }
}