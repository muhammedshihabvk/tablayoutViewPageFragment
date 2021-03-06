package com.shabs.pagepoc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shabs.tablayoutpageviewfragment.APIService

class MainViewModelFactory(private val apiService: APIService,private val movieName:String?) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(apiService,movieName) as T
        }
        throw IllegalArgumentException("unknown ViewModel class")
    }
}