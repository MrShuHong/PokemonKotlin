/*
 * Copyright 2020. hi-dhl (Jack Deng)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hi.dhl.pokemon.data.remote

import com.example.pokemon.data.entity.Article
import com.example.pokemon.data.entity.NetData
import com.example.pokemon.data.entity.NetResult
import com.hi.dhl.pokemon.data.entity.ListingResponse
import com.hi.dhl.pokemon.data.entity.NetWorkPokemonInfo
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */
interface PokemonService {

    //article/list/0/json
    @GET("article/list/{page}/json")
    suspend fun fetchArticleList(@Path("page") page:Int): NetResult<NetData<Article>>

    @GET("article/list/{page}/json")
    suspend fun fetchArticleList2(@Path("page") page:Int): ResponseBody

    @GET("article/list/{page}/json")
    fun fetchArticleList3(@Path("page") page:Int): Observable<ResponseBody>

    @GET("pokemon")
    fun fetchPokemonList(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): ListingResponse

    @GET("pokemon/{name}")
    suspend fun fetchPokemonInfo(@Path("name") name: String): NetWorkPokemonInfo
}