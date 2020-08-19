package com.example.pokemon.ui.binding

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.pokemon.R
import com.example.pokemon.viewmodel.PokemonItemModel


/**
 * Author: shuhong
 * Date: 2020/8/19 10:31
 * Description:
 */

@BindingAdapter("bindClick")
fun bindingClick(view: View, model: PokemonItemModel) {
    view.setOnClickListener {
        Toast.makeText(view.context, model.name, Toast.LENGTH_SHORT).show()
    }
}

@BindingAdapter("bindingAvator")
fun bindingAvator(image: ImageView, url: String) {
    Glide
        .with(image.context)
        .load(url)
        .centerCrop()
        .placeholder(R.mipmap.ic_launcher_round)
        .into(image)
}

