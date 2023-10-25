package com.cmed.characters.Services.LocalRepo

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cmed.characters.Services.Model.StringListConverter
import com.cmed.characters.Services.Model.WandConverter
import com.cmed.characters.Services.Model.responseDataItem
import com.cmed.characters.Services.Repository.HPRemoteKey

@Database(entities = [responseDataItem::class, HPRemoteKey::class], version = 1)
@TypeConverters(WandConverter::class, StringListConverter::class)

abstract class HPDatabase : RoomDatabase(){
    abstract fun responseDao() : HpDao
    abstract fun remoteKeyDao(): RemoteKeyDao
}