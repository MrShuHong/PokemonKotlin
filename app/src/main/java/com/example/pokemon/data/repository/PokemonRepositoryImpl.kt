package com.example.pokemon.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.pokemon.data.net.NetWorkModule
import com.example.pokemon.viewmodel.PokemonItemModel
import com.hi.dhl.pokemon.data.remote.PokemonService
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


/**
 * Author: shuhong
 * Date: 2020/8/19 14:18
 * Description:
 */
class PokemonRepositoryImpl(
    val pagingConfig: PagingConfig,
) : Repository {

    override fun featchPokemonList(): Flow<PagingData<PokemonItemModel>> {

        var map = Pager(config = pagingConfig) {
            PokemonListDataSource()
        }.flow.map { pagingData ->
            pagingData.map {
                PokemonItemModel(name = it.name, url = it.url)
            }
        }

        return map

    }
}