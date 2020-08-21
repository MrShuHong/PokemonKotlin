package com.example.pokemon.data.repository

import androidx.paging.PagingData
import com.example.pokemon.model.PokemonInfoModel
import com.example.pokemon.model.PokemonItemModel
import com.hi.dhl.pokemon.data.remote.PokemonResult
import kotlinx.coroutines.flow.Flow


/**
 * Author: shuhong
 * Date: 2020/8/19 14:14
 * Description:
 */
interface Repository {

    fun fetchPokemonList(): Flow<PagingData<PokemonItemModel>>

    suspend fun fetchPokemonInfo(name: String): Flow<PokemonResult<PokemonInfoModel>>

}