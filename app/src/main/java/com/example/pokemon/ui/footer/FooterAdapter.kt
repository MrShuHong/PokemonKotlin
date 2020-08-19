package com.example.pokemon.ui.footer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.pokemon.R
import com.example.pokemon.ui.PokemonAdapter


/**
 * Author: shuhong
 * Date: 2020/8/19 13:46
 * Description:
 */
class FooterAdapter(val pokemonAdapter: PokemonAdapter) :
    LoadStateAdapter<NetworkStateItemViewHolder>() {

    override fun onBindViewHolder(holder: NetworkStateItemViewHolder, loadState: LoadState) {
        val params = holder.itemView.layoutParams
        if (params is StaggeredGridLayoutManager.LayoutParams){
            params.isFullSpan = true
        }
        holder.bindData(loadState,0)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): NetworkStateItemViewHolder {
        var inflate = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycie_item_network_state, parent, false)
        return NetworkStateItemViewHolder(inflate){
            pokemonAdapter.retry()
        }
    }
}