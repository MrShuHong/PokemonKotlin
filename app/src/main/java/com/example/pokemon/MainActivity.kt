package com.example.pokemon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.pokemon.databinding.ActivityMainBinding
import com.example.pokemon.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val mMainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var bindView =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        //写法一
       /* bindView.mainViewModel = mMainViewModel
        bindView.lifecycleOwner = this*/

        //写法2
        bindView.apply {
            mainViewModel = mMainViewModel
            lifecycleOwner = this@MainActivity
        }

    }
}