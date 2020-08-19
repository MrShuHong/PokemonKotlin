package com.example.pokemon.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.example.pokemon.R
import com.example.pokemon.data.net.NetWorkModule
import com.example.pokemon.databinding.ActivityMainBinding
import com.example.pokemon.ui.footer.FooterAdapter
import com.example.pokemon.viewmodel.MainViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collectLatest

class MainActivity : AppCompatActivity() {

    private val mMainViewModel: MainViewModel by viewModels()
    private val mPokemonAdapter: PokemonAdapter by lazy { PokemonAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var bindView =
            DataBindingUtil.setContentView<ActivityMainBinding>(this,
                R.layout.activity_main
            )
        //写法一
        /* bindView.mainViewModel = mMainViewModel
         bindView.lifecycleOwner = this*/

        //写法2
        bindView.apply {
            recycler_view.adapter = mPokemonAdapter.withLoadStateFooter(FooterAdapter(mPokemonAdapter))
            //recycler_view.adapter = mPokemonAdapter.
            mainViewModel = mMainViewModel
            lifecycleOwner = this@MainActivity
        }

        mMainViewModel.postOfData().observe(this, Observer {
            mPokemonAdapter.submitData(lifecycle,it)
        })

        lifecycleScope.launchWhenCreated {
            mPokemonAdapter.loadStateFlow.collectLatest { state ->
                swipe_refresh.isRefreshing = state.refresh is LoadState.Loading
            }
        }

        var subscribe = NetWorkModule.providePokemonService()
            .fetchArticleList3(0)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(Consumer {
                Log.d("dsh","NetWorkModule"+it.string())
            })
    }
}