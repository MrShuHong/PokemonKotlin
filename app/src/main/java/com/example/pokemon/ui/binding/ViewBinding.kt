package com.example.pokemon.ui.binding

import android.app.Activity
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.example.pokemon.R
import com.example.pokemon.model.PokemonInfoModel
import com.example.pokemon.ui.detail.DetailActivity
import com.example.pokemon.model.PokemonItemModel
import com.example.pokemon.ui.detail.AlbumAdapter
import com.hi.dhl.jprogressview.JProgressView


/**
 * Author: shuhong
 * Date: 2020/8/19 10:31
 * Description:
 */

@BindingAdapter("bindClick")
fun bindingClick(view: View, model: PokemonItemModel) {
    view.setOnClickListener {
       // Toast.makeText(view.context, model.name, Toast.LENGTH_SHORT).show()
        DetailActivity.jumpActivity(view.context,model)
    }
}

@BindingAdapter("bindLoading")
fun bindingLoading(swipe: SwipeRefreshLayout, isLoading: Boolean) {
    //Timber.tag("bindingLoading").e(" isLoading = ${isLoading}")
    swipe.isRefreshing = isLoading
    if (!isLoading) swipe.isEnabled = false
}


@BindingAdapter("bindFinish")
fun bindingFinish(view: View, finish: Boolean) {
    val ctx = view.context
    if (ctx is Activity && finish) {
        view.setOnClickListener { ctx.finish() }
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

@BindingAdapter("bindSmallImage")
fun bindSmallImage(image: ImageView, url: String) {
    Glide
        .with(image.context)
        .load(url)
        .centerCrop()
        .placeholder(R.mipmap.ic_launcher_round)
        .into(image)
}

@BindingAdapter("bindProgressValue","bindProgressMaxValue")
fun bindProgressValue(process:JProgressView, progressValue: Int, maxProgressValue: Int){
    process.setProgress(progressValue.toFloat())
        .setMaxProgress(maxProgressValue)
        .startAnimal()
}

@BindingAdapter("bindAdapter", "bindData")
fun bindingAdapter(
    recyclerView: RecyclerView,
    albumAdapter: AlbumAdapter,
    data: List<PokemonInfoModel.AlbumModel>?
){
    albumAdapter.submitList(data)
    recyclerView.adapter = albumAdapter
}

