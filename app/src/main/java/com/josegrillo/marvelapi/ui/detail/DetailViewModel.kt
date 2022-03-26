package com.josegrillo.marvelapi.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.josegrillo.marvelapi.base.BaseViewModel
import com.josegrillo.marvelapi.entity.CharacterVO
import com.josegrillo.marvelapi.mapper.CharacterMapper
import com.josegrillo.usecase.usecase.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailViewModel(
    private val getCharacterDetailUseCase: GetCharacterDetailUseCase,
    private val getCharacterIsFavoriteUseCase: GetCharacterIsFavoriteUseCase,
    private val updateCharacterFavoriteUseCase: UpdateCharacterFavoriteUseCase,
    private val characterMapper: CharacterMapper
) : BaseViewModel() {

    private val _characterData = MutableLiveData<CharacterVO>()
    val characterData: LiveData<CharacterVO> get() = _characterData
    private val _characterFavorite = MutableLiveData<Boolean>()
    val characterFavorite: LiveData<Boolean> get() = _characterFavorite
    private var isFavoriteCharacter = false

    fun loadCharacterDetail(characterId: Int) {
        compositeDisposable.add(
            getCharacterIsFavoriteUseCase.invoke(characterId).zipWith(
                getCharacterDetailUseCase.invoke(characterId),
                { isFavorite, characterBO ->
                    isFavoriteCharacter = isFavorite
                    characterMapper.map(characterBO, isFavorite)
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _characterData.value = it
                }, {
                    _errorDisplayer.value = it
                })
        )
    }

    fun updateUserFavoriteStatus(characterId: Int) {
        compositeDisposable.add(
            updateCharacterFavoriteUseCase.invoke(characterId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    isFavoriteCharacter = !isFavoriteCharacter
                    _characterFavorite.value = isFavoriteCharacter
                }, {
                    _errorDisplayer.value = it
                })
        )
    }
}