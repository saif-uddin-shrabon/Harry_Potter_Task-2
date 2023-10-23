package com.cmed.characters.Services.api

import com.cmed.characters.Services.Model.responseData
import retrofit2.Response
import retrofit2.http.GET

interface HarryPotterApi {
    @GET("/api/characters")
    suspend fun getHPCharacters() : Response<responseData>

}