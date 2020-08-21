package com.example.pokemon.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import com.example.pokemon.R
import com.example.pokemon.databinding.FragmentDetailBinding
import com.example.pokemon.model.PokemonItemModel


/**
 * Author: shuhong
 * Date: 2020/8/20 10:50
 * Description:
 */
class DetailsFragment : Fragment() {

    private lateinit var mPokemonItemModel: PokemonItemModel
    private val mDetailViewModel : DetailViewModel by activityViewModels()
    private val mAlbumAdapter: AlbumAdapter by lazy { AlbumAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var inflate = DataBindingUtil.inflate<FragmentDetailBinding>(
            inflater,
            R.layout.fragment_detail,
            container,
            false
        )
        mPokemonItemModel = requireNotNull(arguments?.getParcelable(KEY_LIST_ENTITY)) {
            "params is null"
        }
        return inflate.apply {
            albumAdapter = mAlbumAdapter
            pokemonListModel = mPokemonItemModel
            detailModel = mDetailViewModel.apply {
                fetchPokemonInfo2(mPokemonItemModel.name)
                .observe(viewLifecycleOwner, Observer {  })
            }
            lifecycleOwner = this@DetailsFragment
        }.root
    }

    companion object {

        private val KEY_LIST_ENTITY = "listModel"

        fun addFragment(
            manager: FragmentManager,
            model: PokemonItemModel,
            fragmentContainerId: Int
        ) {

            manager.commit {
                var bundleOf = bundleOf(KEY_LIST_ENTITY to model)
                replace(fragmentContainerId, DetailsFragment::class.java, bundleOf)
            }
        }
    }
}