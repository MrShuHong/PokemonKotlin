package com.example.pokemon.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import com.example.databing.DataBindingViewHolder
import com.example.pokemon.R
import com.example.pokemon.databinding.RecycleItemPokemonBinding
import com.example.pokemon.viewmodel.PokemonItemModel


/**
 * Author: shuhong
 * Date: 2020/8/18 17:24
 * Description:
 */

class PokemonAdapter :
    PagingDataAdapter<PokemonItemModel, PokemonItemViewHolder>(PokemonItemModel.diffCallback) {

    override fun onBindViewHolder(holder: PokemonItemViewHolder, position: Int) {
        var item = getItem(position)
        item?.let { holder.bindData(it,position) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonItemViewHolder {

        var inflater = LayoutInflater.from(parent.context)
        var itemView = inflater.inflate(R.layout.recycle_item_pokemon, parent, false)
        return PokemonItemViewHolder(itemView)
    }

}

class PokemonItemViewHolder(itemView: View) : DataBindingViewHolder<PokemonItemModel>(itemView) {

   private val mBinding: RecycleItemPokemonBinding by viewHolderBinding(itemView)

    override fun bindData(data: PokemonItemModel, position: Int) {

        mBinding.apply {
            data.id = "#${position + 1}"
            pokemon = data
            executePendingBindings()
        }
    }

}

/*class PokemonItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val mBinding: RecycleItemPokemonBinding by lazy {
        requireNotNull(DataBindingUtil.bind<RecycleItemPokemonBinding>(itemView))
    }

}*/

