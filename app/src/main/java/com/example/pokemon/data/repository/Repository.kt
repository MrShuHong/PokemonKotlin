package com.example.pokemon.data.repository

import androidx.paging.PagedList
import androidx.paging.PagingData
import com.example.pokemon.viewmodel.PokemonItemModel
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow


/**
 * Author: shuhong
 * Date: 2020/8/19 14:14
 * Description:
 */
interface Repository {

    fun featchPokemonList(): Flow<PagingData<PokemonItemModel>>

}