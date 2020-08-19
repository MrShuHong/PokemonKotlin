package com.example.pokemon.data.repository

import android.util.Log
import androidx.paging.PagingSource
import com.example.pokemon.data.net.NetWorkModule
import com.google.gson.Gson
import com.hi.dhl.pokemon.data.entity.ListingData


/**
 * Author: shuhong
 * Date: 2020/8/19 15:51
 * Description:
 */
class PokemonListDataSource : PagingSource<Int, ListingData>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ListingData> {
        return try {
            val page = params.key ?: 0
            //获取网络数据
            Log.d("dsh", "PokemonListDataSource")
            Log.d("dsh", "page "+page)
            var body = NetWorkModule.providePokemonService().fetchArticleList2(page)
            Log.d("dsh", "netData1 " + body.string())

            var netData = NetWorkModule.providePokemonService().fetchArticleList(page)
            Log.d("dsh", "netData2 " + Gson().toJson(netData))
            var fetchPokemonList = NetWorkModule.providePokemonService()
                .fetchPokemonList()
            Log.d("dsh", "fetchPokemonList " + Gson().toJson(fetchPokemonList))
            val result = fetchPokemonList.results
            Log.d("dsh", "result" + Gson().toJson(result))
            LoadResult.Page(
                //需要加载的数据
                data = result,
                //如果可以往上加载更多就设置该参数，否则不设置
                prevKey = null,
                //加载下一页的key 如果传null就说明到底了
                nextKey = result?.size
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }

    }
}