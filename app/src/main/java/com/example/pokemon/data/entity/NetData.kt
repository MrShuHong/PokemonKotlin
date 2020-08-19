package com.example.pokemon.data.entity


/**
 * Author: shuhong
 * Date: 2020/8/19 17:21
 * Description:
 */
data class NetData<T>(
    val curPage: Int,
    val datas: List<T>,
    val offset: Int,
    val pageCount: Int,
    val size: Int,
    val total: Int,
    val over: Boolean
)