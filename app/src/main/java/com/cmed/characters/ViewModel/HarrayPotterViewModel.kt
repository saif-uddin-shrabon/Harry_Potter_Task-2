package com.cmed.characters.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmed.characters.Services.Repository.HarryPotterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HarrayPotterViewModel @Inject constructor (private val harryPotterRepository: HarryPotterRepository) : ViewModel() {

    val potterLiveData get() =  harryPotterRepository.potterLiveData



    fun getHPCharacter(){
        viewModelScope.launch {
            harryPotterRepository.getHPCharacter()

            println("janina: "+harryPotterRepository.getHPCharacter().toString())
        }
    }


}