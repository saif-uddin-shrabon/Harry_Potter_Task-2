package com.cmed.characters.Services.LocalRepository.DB

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cmed.characters.Services.Repository.HPRemoteKey



@Dao
interface RemoteKeyDao {

    @Query("SELECT*FROM HPRemoteKey WHERE id =:id")
    suspend fun getRemoteKey(id: String): HPRemoteKey

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<HPRemoteKey>)

    @Query("DELETE FROM HPRemoteKey")
    suspend fun deleteAllRemoteKey()

}