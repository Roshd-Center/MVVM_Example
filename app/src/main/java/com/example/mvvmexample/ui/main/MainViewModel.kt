package com.example.mvvmexample.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmexample.data.TrashRepository
import kotlinx.coroutines.*

class MainViewModel constructor(private val trashRepository: TrashRepository) : ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val trashList = trashRepository.trashes
    var job: Job? = null
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    val loading = MutableLiveData<Boolean>()

    fun refreshTrashes() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = trashRepository.getTrashes()
            withContext(Dispatchers.IO) {
                if (response.isSuccessful) {
                    response.body()?.let { trashRepository.updateDbTrash(it) }
                    withContext(Dispatchers.Main){
                        loading.value = false
                    }
                } else {
                    onError("Error : ${response.message()}\n${response.body()}\n${response}\n${response.raw()}")
                }
            }
        }

    }

    private fun onError(message: String) {
        Log.e("TEST OnError", message)
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}