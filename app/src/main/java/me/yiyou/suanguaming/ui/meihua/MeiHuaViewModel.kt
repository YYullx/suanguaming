package me.yiyou.suanguaming.ui.meihua

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.yiyou.suanguaming.database.MeiHuaRepository

class MeiHuaViewModel(private val repository: MeiHuaRepository) :ViewModel(){

    // 插入
    fun insert(meiHuaBean: MeiHuaBean) = viewModelScope.launch {
        withContext(Dispatchers.IO){
            repository.insert(meiHuaBean)
        }
    }

    // 查询
    val all: LiveData<List<MeiHuaBean>> = repository.allList.asLiveData()

    // 删除
    fun delete(meiHuaBean: MeiHuaBean) = viewModelScope.launch {
        withContext(Dispatchers.IO){
            repository.delete(meiHuaBean)
        }
    }
}

class MeiHuaViewModelFactory(private val repository: MeiHuaRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MeiHuaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MeiHuaViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}