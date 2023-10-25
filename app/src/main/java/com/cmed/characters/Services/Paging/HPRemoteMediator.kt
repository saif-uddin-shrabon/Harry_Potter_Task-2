package com.cmed.characters.Services.Paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.cmed.characters.Services.LocalRepo.HPDatabase
import com.cmed.characters.Services.Model.responseDataItem
import com.cmed.characters.Services.Repository.HPRemoteKey
import com.cmed.characters.Services.api.HarryPotterApi


@OptIn(ExperimentalPagingApi::class)
class HPRemoteMediator(
    private val harryPotterApi: HarryPotterApi,
    private  val hpDatabase: HPDatabase

): RemoteMediator <Int, responseDataItem>() {

    val hpDao = hpDatabase.responseDao()
    val hpRemoteKeyDao = hpDatabase.remoteKeyDao()
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, responseDataItem>
    ): MediatorResult {


        //Fetch Quotes from API
        // Save these Quotes + RemoteKeys Data into DB
    return  try{

         // Logic for states - Refresh, Prepend, apend
            val currentPage = when(loadType){
                LoadType.REFRESH ->{
                    val remotKeys = getRemotKeyClosesToCurrentPosition(state)
                    remotKeys?.nextPage?.minus(1) ?: 1

                }
                LoadType.PREPEND -> {
                    val remotKeys = getRemoteKeyForFirstItem(state)
                    val  prevPage = remotKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remotKeys != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage =  remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }


        val response = harryPotterApi.getHPCharacter(currentPage)
        val data = response.body() ?: emptyList()
        val endOfPaginationReached = data.isEmpty()


            val prevPage = if(currentPage == 1) null else currentPage -1
            val nextPage = if(endOfPaginationReached) null else currentPage + 1

             hpDatabase.withTransaction {

                 if (loadType == LoadType.REFRESH){
                     hpDao.deleteHP()
                     hpRemoteKeyDao.deleteAllRemoteKey()
                 }

               hpDao.addHP(data)
                 val keys = data.map {
                    HPRemoteKey(
                        id = it.id,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )


                 }
                 hpRemoteKeyDao.addAllRemoteKeys(keys)
             }
            MediatorResult.Success(endOfPaginationReached)
        }catch (e: Exception){
             MediatorResult.Error(e)
        }



    }

    private suspend fun getRemotKeyClosesToCurrentPosition(
        state: PagingState<Int, responseDataItem>
    ): HPRemoteKey? {
        return state.anchorPosition?.let { position->
            state.closestItemToPosition(position)?.id?.let {id ->
                hpRemoteKeyDao.getRemoteKey(id = id)
            }
        }

    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, responseDataItem>
    ): HPRemoteKey? {
        return  state.pages.firstOrNull {it.data.isNotEmpty()}?.data?.firstOrNull()
            ?.let { hp ->
                hpRemoteKeyDao.getRemoteKey(id =  hp.id)
            }

    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, responseDataItem>
    ): HPRemoteKey? {

        return  state.pages.lastOrNull {it.data.isNotEmpty()}?.data?.lastOrNull()
            ?.let { hp ->
                hpRemoteKeyDao.getRemoteKey(id =  hp.id)
            }

    }
}