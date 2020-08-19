package com.example.pokemon.data.net

import com.hi.dhl.pokemon.data.remote.PokemonService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Author: shuhong
 * Date: 2020/8/19 14:35
 * Description:
 */
object NetWorkModule {

    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://pokeapi.co/api/v2/")
            //.baseUrl("https://www.wanandroid.com/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    fun providePokemonService(): PokemonService {
        var okHttpClient = provideOkHttpClient()
        var provideRetrofit = provideRetrofit(okHttpClient)
        /*var retrofit = Retrofit.Builder()
            .client(OkHttpClient.Builder()
                .build())
            .baseUrl("https://www.wanandroid.com/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()*/

        return provideRetrofit.create(PokemonService::class.java)
    }
}