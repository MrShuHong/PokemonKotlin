package com.example.databing

import android.content.Context
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import java.text.FieldPosition
import kotlin.jvm.Throws


/**
 * Author: shuhong
 * Date: 2020/8/19 9:55
 * Description:
 */
abstract class DataBindingViewHolder<T>(val view: View) : RecyclerView.ViewHolder(view) {

    @Throws(Exception::class)
    abstract fun bindData(data:T,position: Int)

    inline fun <reified T : ViewDataBinding> viewHolderBinding(itemView: View): Lazy<T> =
        lazy {
            requireNotNull(DataBindingUtil.bind<T>(itemView)) { "cannot find the matched layout." }
        }

}