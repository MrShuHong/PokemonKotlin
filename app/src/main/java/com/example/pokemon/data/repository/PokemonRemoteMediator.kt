package com.example.pokemon.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.hi.dhl.pokemon.data.entity.PokemonEntity
import com.hi.dhl.pokemon.data.remote.PokemonService
import retrofit2.HttpException
import java.io.IOException


/**
 * Author: shuhong
 * Date: 2020/8/19 14:45
 * Description:
 */

@OptIn(ExperimentalPagingApi::class)
class PokemonRemoteMediator(
    val api: PokemonService
) : RemoteMediator<Int, PokemonEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonEntity>
    ): MediatorResult {
        try {

            /**
             * 在这个方法内将会做三件事
             *
             * 1. 参数 LoadType 有个三个值，关于这三个值如何进行判断
             *      LoadType.REFRESH
             *      LoadType.PREPEND
             *      LoadType.APPEND
             *
             * 2. 请问网络数据
             *
             * 3. 将网路插入到本地数据库中
             */


            // 第一步： 判断 LoadType
            val pageKey = when (loadType) {
                // 首次访问 或者调用 PagingDataAdapter.refresh()
                LoadType.REFRESH -> null

                // 在当前加载的数据集的开头加载数据时
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)

                LoadType.APPEND -> { // 下来加载更多时触发

                    /**
                     *
                     * 这里主要获取下一页数据的开始位置，可以理解为从什么地方开始加载下一页数据
                     * 这里有两种方式来获取下一页加载数据的位置
                     * 方式一：这种方式比较简单，当前页面最后一条数据是下一页的开始位置
                     * 方式二：比较麻烦，当前分页数据没有对应的远程 key，这个时候需要我们自己建表,
                     */

                    /**
                     * 方式一：这种方式比较简单，当前页面最后一条数据是下一页的开始位置
                     * 通过 load 方法的参数 state 获取当页面最后一条数据
                     */
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        return MediatorResult.Success(
                            endOfPaginationReached = true
                        )
                    }
                    lastItem.page
                }
            }



            // 第二步： 请问网络分页数据
            val page = pageKey ?: 0
            val result = api.fetchPokemonList(
                state.config.pageSize,
                page * state.config.pageSize
            ).results

            val endOfPaginationReached = result.isEmpty()

            val item = result.map {
                PokemonEntity(
                    name = it.name,
                    url = it.getImageUrl(),
                    remoteName = remotePokemon,
                    page = page + 1
                )
            }


            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: IOException) {
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            return MediatorResult.Error(e)
        }
    }

    companion object {
        private val TAG = "PokemonRemoteMediator"
        private val remotePokemon = "pokemon"
    }


}