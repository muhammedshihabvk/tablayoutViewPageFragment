package com.shabs.pagepoc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.shabs.tablayoutpageviewfragment.APIService

class MainViewModel(private val apiService: APIService,private val moviewName :String?) : ViewModel(){

    val listData = Pager(PagingConfig(pageSize = 6)){
        PostDataSource(apiService,moviewName)
    }.flow.cachedIn(viewModelScope)
}