package com.example.pokemon.ui.detail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory


/**
 * Author: shuhong
 * Date: 2020/8/20 10:48
 * Description:
 */
class CustomFragmentFactory : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
      return  when (className){
            DetailsFragment::class.java.name -> DetailsFragment()
            else -> super.instantiate(classLoader, className)
        }
    }
}