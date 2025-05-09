package me.yiyou.suanguaming.ui.setting

import android.os.Bundle
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

        binding.switchButton.setOnCheckedChangeListener({ buttonView, isChecked ->
            if (isChecked){
                mmkv.encode("autoAnswer", true)
                println("switchButton:true")
            }else{
                mmkv.encode("autoAnswer", false)
                println("switchButton:false")
            }
        })

        if (mmkv.decodeBool("autoAnswer", false)) {
            binding.switchButton.isChecked = true
        } else {
            binding.switchButton.isChecked = false
        }
    }
}