package me.yiyou.suanguaming.ui.memory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MemoryViewModel : ViewModel() {

    // 使用MutableLiveData来持有数字
    private val _number = MutableLiveData<Int>(0)

    // 提供一个不可变的LiveData给外部使用，以避免外部直接修改数据
    val number: LiveData<Int> = _number

    // 更新数据的方法
    fun updateNumber(newValue: Int) {
        _number.value = newValue
    }
}