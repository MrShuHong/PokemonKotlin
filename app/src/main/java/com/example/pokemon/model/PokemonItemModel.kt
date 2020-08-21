package com.example.pokemon.model

import android.os.Parcelable
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DiffUtil
import kotlinx.android.parcel.Parcelize


/**
 * Author: shuhong
 * Date: 2020/8/18 17:27
 * Description:
 */
@Parcelize
data class PokemonItemModel(
    var id: String = "",
    val name: String,
    val url: String
) : ViewModel(), Parcelable {
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