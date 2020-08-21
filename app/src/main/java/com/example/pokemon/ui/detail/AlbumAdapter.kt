package com.example.pokemon.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.databing.DataBindingViewHolder
import com.example.pokemon.R
import com.example.pokemon.databinding.RecycleItemAlbumBinding
import com.example.pokemon.model.PokemonInfoModel


/**
 * Author: shuhong
 * Date: 2020/8/20 14:36
 * Description:
 */

class AlbumAdapter :
    ListAdapter<PokemonInfoModel.AlbumModel, AlbumViewHolder>(PokemonInfoModel.AlbumModel.diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        var view = inflater.inflate(R.layout.recycle_item_album, parent, false)
        return AlbumViewHolder(view)

    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        var item = getItem(position)
        item?.let {
            holder.bindData(item, position)
        }
    }


}

class AlbumViewHolder(view: View) : DataBindingViewHolder<PokemonInfoModel.AlbumModel>(view) {

    private val mAlbumModel: RecycleItemAlbumBinding by viewHolderBinding(view)

    override fun bindData(data: PokemonInfoModel.AlbumModel, position: Int) {
        mAlbumModel.apply {
            album = data
            executePendingBindings()
        }
    }

}