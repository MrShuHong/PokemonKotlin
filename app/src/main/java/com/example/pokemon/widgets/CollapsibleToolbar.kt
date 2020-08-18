package com.example.pokemon.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import com.google.android.material.appbar.AppBarLayout


/**
 * Author: shuhong
 * Date: 2020/8/18 16:33
 * Description:
 */
class CollapsibleToolbar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : MotionLayout(context, attrs, defStyleAttr),AppBarLayout.OnOffsetChangedListener {

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        progress = -verticalOffset / appBarLayout?.totalScrollRange?.toFloat()!!
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        (parent as AppBarLayout)?.addOnOffsetChangedListener(this)
    }
}