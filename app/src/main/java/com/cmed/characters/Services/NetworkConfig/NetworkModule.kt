package com.cmed.characters.Services.NetworkConfig

import com.cmed.characters.Utils.Constant.BASE_URL
import com.cmed.characters.Services.api.HarryPotterApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {


    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun provideHPCharaters(retrofit: Retrofit) : HarryPotterApi {
        return  retrofit.create(HarryPotterApi::class.java)
    }

}


