package com.example.pokemon.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.pokemon.data.PokemonFactory


/**
 * Author: shuhong
 * Date: 2020/8/18 15:53
 * Description:
 */
class MainViewModel : ViewModel() {

    fun postOfData(): LiveData<PagingData<PokemonItemModel>>{
        return PokemonFactory.makePokemonRepository()
            .featchPokemonList().cachedIn(viewModelScope).asLiveData()
    }
}