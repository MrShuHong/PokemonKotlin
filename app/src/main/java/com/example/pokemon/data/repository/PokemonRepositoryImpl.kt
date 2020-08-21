package com.example.pokemon.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.pokemon.data.entity.PokemonInfoEntity
import com.example.pokemon.data.mapper.Mapper
import com.example.pokemon.data.net.NetWorkModule
import com.example.pokemon.model.PokemonInfoModel
import com.example.pokemon.model.PokemonItemModel
import com.hi.dhl.pokemon.data.entity.PokemonEntity
import com.hi.dhl.pokemon.data.remote.PokemonResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map


/**
 * Author: shuhong
 * Date: 2020/8/19 14:18
 * Description:
 */
class PokemonRepositoryImpl(
    val pagingConfig: PagingConfig,
    val mapper2ItemMolde: Mapper<PokemonEntity, PokemonItemModel>,
    val mapper2InfoModel: Mapper<PokemonInfoEntity, PokemonInfoModel>
) : Repository {

    override fun fetchPokemonList(): Flow<PagingData<PokemonItemModel>> {

        return Pager(config = pagingConfig) {
            PokemonListDataSource()
        }.flow.map { pagingData ->
            pagingData.map {entity ->
                mapper2ItemMolde.map(entity)
            }
        }
    }

    override suspend fun fetchPokemonInfo(name: String): Flow<PokemonResult<PokemonInfoModel>> {
        return flow {
            try{
                var fetchPokemonInfo = NetWorkModule
                    .providePokemonService()
                    .fetchPokemonInfo(name)
                var pokemonInfoEntity =
                    PokemonInfoEntity.convert2PokemonInfoEntity(fetchPokemonInfo)
                var infoModel = mapper2InfoModel.map(pokemonInfoEntity)

                emit(PokemonResult.Success(infoModel))
            }catch (e:Exception){
                emit(PokemonResult.Failure(e))
            }
        }.flowOn(Dispatchers.IO)
    }
}