package com.josegrillo.usecase.repository

import androidx.paging.PagingData
import com.josegrillo.usecase.entity.CharacterBO
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface CharactersRepository {
    fun getCharacters(): Flowable<PagingData<CharacterBO>>
    fun getCharacterDetail(characterId: Int): Single<CharacterBO>
    fun checkFavoriteCharacter(characterId: Int): Single<Boolean>
    fun insertFavorite(characterId: Int): Completable
    fun deleteFavorite(characterId: Int): Completable
}