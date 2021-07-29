package com.shabs.pagepoc

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.shabs.pagepoc.models.Data
import com.shabs.tablayoutpageviewfragment.APIService

class PostDataSource(private val apiService: APIService,private val movieName : String?) :
    PagingSource<Int, Data>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {

        try {
            val currentLoadingPageKey = params.key ?: 1
            val response = apiService.getListData(
                currentLoadingPageKey, movieName, "132c797b"
            )

            val data = response.body()?.search ?: emptyList()

            val responseData = mutableListOf<Data>()
            responseData.addAll(data)

            val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - 1
            Log.d("TAG",currentLoadingPageKey.toString())
            return LoadResult.Page(data, prevKey, currentLoadingPageKey.plus(1))
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        TODO("Not yet implemented")
    }
}