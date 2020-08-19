package com.example.pokemon.ui.footer

import android.view.View
import androidx.core.view.isVisible
import androidx.paging.LoadState
import com.example.databing.DataBindingViewHolder
import com.example.pokemon.databinding.RecycieItemNetworkStateBinding


/**
 * Author: shuhong
 * Date: 2020/8/19 13:49
 * Description:
 */
class NetworkStateItemViewHolder(view: View, private val retryCallback: () -> Unit) :
    DataBindingViewHolder<LoadState>(view) {

    private val mBinding: RecycieItemNetworkStateBinding by viewHolderBinding(view)

    override fun bindData(data: LoadState, position: Int) {
        mBinding.apply {
            progress.isVisible = data is LoadState.Loading
            retryButton.isVisible = data is LoadState.Error
            retryButton.setOnClickListener {
                retryCallback()
            }
            //!(data as? LoadState.Error)?.error?.message.isNullOrBlank()
            errorMsg.isVisible = !(data as? LoadState.Error)?.error?.message.isNullOrBlank()
            errorMsg.text = (data as? LoadState.Error)?.error?.message
            executePendingBindings()
        }
    }

}