package com.example.pokemon.viewmodel

import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DiffUtil


/**
 * Author: shuhong
 * Date: 2020/8/18 17:27
 * Description:
 */
class PokemonItemModel(
    var id: String = "",
    val name: String,
    val url: String
) : ViewModel() {
    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<PokemonItemModel>() {
            override fun areItemsTheSame(
                oldItem: PokemonItemModel,
                newItem: PokemonItemModel
            ): Boolean =
                oldItem.name == newItem.name


            override fun areContentsTheSame(
                oldItem: PokemonItemModel,
                newItem: PokemonItemModel
            ): Boolean = oldItem == newItem

        }
    }
}