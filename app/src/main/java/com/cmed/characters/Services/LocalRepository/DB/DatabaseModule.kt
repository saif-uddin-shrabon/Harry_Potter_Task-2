package com.cmed.characters.Services.LocalRepository.DB

import android.content.Context
import android.icu.text.RelativeDateTimeFormatter.RelativeDateTimeUnit
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabae(@ApplicationContext context: Context) : HPDatabase{
        return Room.databaseBuilder(context, HPDatabase::class.java, "HarryPotter").build()
    }


}