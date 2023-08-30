package com.demo.newsdemo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.newsdemo.model.NewsData
import com.demo.newsdemo.network.MainRepo
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private var newsDataPrivate = MutableLiveData<NewsData>()
    var newsData : LiveData<NewsData> = newsDataPrivate
    var repo = MainRepo

    fun getNewsData() = viewModelScope.launch {
        val response = repo.getNewsData()
        if (response.code()==200) {
            newsDataPrivate.postValue(response.body())
        }
    }
}