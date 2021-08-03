package com.example.mvvmexample.ui.home

import androidx.lifecycle.*

class HomeViewModel : ViewModel() , LifecycleObserver{

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public fun test() {
        _text.apply { value = "Resumed!" }
    }
}