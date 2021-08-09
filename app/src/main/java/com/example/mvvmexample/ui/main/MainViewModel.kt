package com.example.mvvmexample.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmexample.data.MainRepository
import com.example.mvvmexample.data.models.db.Trash
import kotlinx.coroutines.*

class MainViewModel constructor(private val mainRepository: MainRepository) : ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val trashList = MutableLiveData<List<Trash>>()
    var job: Job? = null
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    val loading = MutableLiveData<Boolean>()

    fun getTrashes() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = mainRepository.getTrashes()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    trashList.postValue(response.body())
                    loading.value = false
                } else {
                    onError("Error : ${response.message()}\n${response.body()}\n${response}\n${response.raw()}")
                }
            }
        }

    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}