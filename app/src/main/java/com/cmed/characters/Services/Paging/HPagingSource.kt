package com.cmed.characters.Services.Paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cmed.characters.Services.Model.responseData
import com.cmed.characters.Services.api.HarryPotterApi

class HPagingSource(val harryPotterApi: HarryPotterApi) : PagingSource<Int,responseData>() {
    override fun getRefreshKey(state: PagingState<Int, responseData>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, responseData> {
        TODO("Not yet implemented")
    }
}