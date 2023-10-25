package com.cmed.characters.Services.LocalRepository.DB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cmed.characters.Services.Model.responseDataItem
import com.cmed.characters.Services.Repository.HPRemoteKey

@Database(entities = [responseDataItem::class, HPRemoteKey::class], version = 1)
abstract class HPDatabase : RoomDatabase(){
    abstract fun responseDao() : HpDao
    abstract fun remoteKeyDao(): RemoteKeyDao
}