package com.cmed.characters.Services.LocalRepo

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cmed.characters.Services.Model.responseDataItem

@Dao
interface HpDao {
    @Query("SELECT*FROM HarryPotter")
    fun getHP(): PagingSource<Int, responseDataItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHP(offlineHP: List<responseDataItem>)

    @Query("DELETE FROM HarryPotter")
    suspend fun deleteHP()
}