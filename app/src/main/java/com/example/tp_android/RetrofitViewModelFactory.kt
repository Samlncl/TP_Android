package com.example.tp_android

import RetrofitViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tp_android.Network.RetrofitService

class RetrofitViewModelFactory(
    private val retrofitService: RetrofitService
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RetrofitViewModel::class.java)) {
            return RetrofitViewModel(retrofitService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
