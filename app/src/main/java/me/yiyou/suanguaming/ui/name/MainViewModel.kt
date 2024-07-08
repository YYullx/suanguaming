package me.yiyou.suanguaming.ui.name

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.yiyou.suanguaming.Tiku
import me.yiyou.suanguaming.database.TikuRepository

class MainViewModel(private val repository: TikuRepository) : ViewModel() {

    // 插入
    fun insert(tiku: Tiku) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            repository.insert(tiku)
        }
    }

    // 使用livedata实时监听数据并更新
    val allTiku: LiveData<List<Tiku>> = repository.allList.asLiveData()

    // 删除数据
    fun delete(tiku: Tiku) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            repository.delete(tiku)
        }
    }
}

class MainViewModelFactory(private val repository: TikuRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}