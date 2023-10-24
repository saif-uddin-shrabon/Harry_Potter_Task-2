package com.cmed.characters.Services.Repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.liveData
import com.cmed.characters.Services.Model.responseData
import com.cmed.characters.Services.Paging.HPagingSource
import com.cmed.characters.Services.api.HarryPotterApi
import com.cmed.characters.Utils.NetworkResult
import okio.IOException
import org.json.JSONObject
import retrofit2.HttpException
import java.util.concurrent.TimeoutException
import javax.inject.Inject

class HarryPotterRepository @Inject constructor(private val harryPotterApi: HarryPotterApi) {

    private val _potterLiveData  =  MutableLiveData<NetworkResult<responseData>>()
    val potterLiveData get() = _potterLiveData

    suspend fun getHPCharacter(){
        _potterLiveData.postValue(NetworkResult.Loading())

        try {
            val response = harryPotterApi.getHPCharacters()
               val rest = response.body()
                println("checkresponse: "+rest.toString())



            if(response.isSuccessful && response.body() != null){
                _potterLiveData.postValue(NetworkResult.Success(response.body()!!))

            }else if(response.errorBody() != null){
                val errorObj =  JSONObject(response.errorBody()!!.charStream().readText())
                _potterLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
            }else{
                _potterLiveData.postValue(NetworkResult.Error("Something wen wrong"))
            }
        }catch (e: IOException){
         _potterLiveData.postValue(NetworkResult.Error("No internet Connection"))
        }catch (e: TimeoutException){
            _potterLiveData.postValue(NetworkResult.Error("Request Timeout"))
        }catch (e: HttpException){
            _potterLiveData.postValue(NetworkResult.Error("Unexpected response"))
        }
    }

    //paging
    fun getHPages() = Pager(
        config = PagingConfig(pageSize = 10, maxSize = 100),
        pagingSourceFactory = {HPagingSource(harryPotterApi)}
    ).liveData

}