package com.example.pokemon.data.repository

import android.util.Log
import androidx.paging.PagingSource
import com.example.pokemon.data.net.NetWorkModule
import com.google.gson.Gson
import com.hi.dhl.pokemon.data.entity.ListingData
import com.hi.dhl.pokemon.data.entity.PokemonEntity


/**
 * Author: shuhong
 * Date: 2020/8/19 15:51
 * Description:
 */
class PokemonListDataSource : PagingSource<Int, PokemonEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonEntity> {
        return try {

            val page = params.key ?: 0
            //获取网络数据
            Log.d("dsh", "PokemonListDataSource ${params.key}")
            Log.d("dsh", "PokemonListDataSource ${params.loadSize}")

            /*var netData = NetWorkModule.providePokemonService().fetchArticleList(page)
            Log.d("dsh", "netData2 " + Gson().toJson(netData))*/
            var fetchPokemonList = NetWorkModule.providePokemonService()
                .fetchPokemonList(params.loadSize, page)
            fetchPokemonList.count
            Log.d("dsh", "PokemonListDataSource count = ${fetchPokemonList.count}")
            Log.d("dsh", "PokemonListDataSource next = ${fetchPokemonList.next}")
            Log.d("dsh", "PokemonListDataSource previous = ${fetchPokemonList.previous}")

            val result = fetchPokemonList.results
            var items = result.map {
                PokemonEntity(
                    name = it.name,
                    url = it.getImageUrl(),
                    page = page + 1,
                    remoteName = "pokemon"
                )
            }
            LoadResult.Page(
                //需要加载的数据
                data = items,
                //如果可以往上加载更多就设置该参数，否则不设置
                prevKey = null,
                //加载下一页的key 如果传null就说明到底了
                nextKey =  if(result.isEmpty()) null else page+1
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }

    }
}