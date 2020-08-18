package com.example.pokemon

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.viewmodel.PokemonItemModel


/**
 * Author: shuhong
 * Date: 2020/8/18 17:24
 * Description:
 */

class PokemonAdapter : PagingDataAdapter<PokemonItemModel, PokemonItemViewHolder>(PokemonItemModel.diffCallback) {

    override fun onBindViewHolder(holder: PokemonItemViewHolder, position: Int) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonItemViewHolder {

        //todo 这里有问题
        return PokemonItemViewHolder(parent)
    }

}

class PokemonItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


}