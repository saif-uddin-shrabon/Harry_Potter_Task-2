package com.cmed.characters.Services.Paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cmed.characters.Services.Model.responseDataItem
import com.cmed.characters.Services.api.HarryPotterApi

class HPagingSource(private val harryPotterApi: HarryPotterApi) : PagingSource<Int, responseDataItem>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, responseDataItem> {
        return try {
            val position = params.key ?: 1
            val response = harryPotterApi.getHPCharacter(position)


            if (response.isSuccessful) {
                val data = response.body() ?: emptyList()
                val nextKey = if (data.isEmpty()) null else position + 1

                LoadResult.Page(
                    data = data,
                    prevKey = if (position == 1) null else position - 1,
                    nextKey = nextKey
                )
            } else {

                LoadResult.Error(Exception("Failed to load data"))
            }
        } catch (e: Exception) {

            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, responseDataItem>): Int? {val anchorPosition = state.anchorPosition ?: return null

        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}
