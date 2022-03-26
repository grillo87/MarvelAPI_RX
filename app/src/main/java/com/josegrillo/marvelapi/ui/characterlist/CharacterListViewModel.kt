package com.josegrillo.marvelapi.ui.characterlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import com.josegrillo.marvelapi.base.BaseViewModel
import com.josegrillo.marvelapi.entity.CharacterVO
import com.josegrillo.marvelapi.mapper.CharacterMapper
import com.josegrillo.marvelapi.utils.Event
import com.josegrillo.usecase.usecase.GetCharactersUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi

class CharacterListViewModel(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val characterMapper: CharacterMapper
) : BaseViewModel() {
    private val _charactersData = MutableLiveData<PagingData<CharacterVO>>()
    private val _selectedCharacterId = MutableLiveData<Event<Int>>()
    val selectedCharacterId: LiveData<Event<Int>> get() = _selectedCharacterId
    val charactersData: LiveData<PagingData<CharacterVO>> get() = _charactersData

    @ExperimentalCoroutinesApi
    fun loadCharacters() {
        compositeDisposable.add(
            getCharactersUseCase.invoke(viewModelScope)
                .map { it.map(characterMapper::map) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _charactersData.value = it
                }, {
                    _errorDisplayer.value = it
                })
        )
    }

    fun onCharacterSelected(characterVO: CharacterVO) {
        _selectedCharacterId.value = Event(characterVO.id)
    }
}