package com.example.pokemon.data.mapper


/**
 * Author: shuhong
 * Date: 2020/8/20 14:08
 * Description:
 */
interface Mapper<I, O> {
    fun map(input: I): O
}