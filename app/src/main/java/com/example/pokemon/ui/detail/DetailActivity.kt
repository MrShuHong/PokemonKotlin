package com.example.pokemon.ui.detail

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.pokemon.R
import com.example.pokemon.databinding.ActivityDetailBinding
import com.example.pokemon.model.PokemonItemModel
import org.jetbrains.anko.startActivity

class DetailActivity : AppCompatActivity() {

    lateinit var mPokemonItemModel: PokemonItemModel

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = CustomFragmentFactory()
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_detail)
        var mDetailBinding =
            DataBindingUtil.setContentView<ActivityDetailBinding>(this, R.layout.activity_detail)

        mDetailBinding.apply {
            mPokemonItemModel = requireNotNull(intent.getParcelableExtra(KEY_LIST_ENTITY)){
                "params is null"
            }

            DetailsFragment.addFragment(
                supportFragmentManager,
                mPokemonItemModel,
                R.id.fragment_container
            )
        }
    }

    companion object {
        private val KEY_LIST_ENTITY = "listModel"
        fun jumpActivity(context: Context, model: PokemonItemModel) {
            context.startActivity<DetailActivity>(KEY_LIST_ENTITY to model)
        }
    }
}