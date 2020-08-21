package com.example.pokemon.ui.detail

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.pokemon.data.PokemonFactory
import com.example.pokemon.data.repository.Repository
import com.example.pokemon.model.PokemonInfoModel
import com.hi.dhl.pokemon.data.remote.doFailure
import com.hi.dhl.pokemon.data.remote.doSuccess
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart


/**
 * Author: shuhong
 * Date: 2020/8/20 11:12
 * Description:
 */
class DetailViewModel(): ViewModel() {

     val mLoading = ObservableBoolean()

    private val _pokemon = MutableLiveData<PokemonInfoModel>()

    val pokemon : MutableLiveData<PokemonInfoModel> = _pokemon

    private val _failure = MutableLiveData<String>()

    val failure : MutableLiveData<String> = _failure


    fun fetchPokemonInfo2(name:String) = liveData<PokemonInfoModel> {
        PokemonFactory.makePokemonRepository().fetchPokemonInfo(name)
            .onStart {
                mLoading.set(true)
            }
            .catch {
                mLoading.set(false)
            }
            .onCompletion {
                mLoading.set(false)
            }
            .collectLatest {result ->
                result.doFailure {throwable ->
                    _failure.value = throwable?.message ?: "failure"
                }
                result.doSuccess {value ->
                    _pokemon.postValue(value)
                    emit(value)
                }
            }
    }
}