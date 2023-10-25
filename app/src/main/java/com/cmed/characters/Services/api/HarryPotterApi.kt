package com.cmed.characters.Services.api

import com.cmed.characters.Services.Model.responseData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HarryPotterApi {
    @GET("/api/characters")
    suspend fun getHPCharacters() : Response<responseData>

    @GET("/api/characters")
    suspend fun getHPCharacter(@Query("page") page : Int) : responseData

}