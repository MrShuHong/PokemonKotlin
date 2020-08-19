package com.example.pokemon.data.entity


/**
 * Author: shuhong
 * Date: 2020/8/19 17:18
 * Description:
 */
data class NetResult<T>(val data: T, val errorCode: Int, val errorMsg: String)