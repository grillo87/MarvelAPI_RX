package com.josegrillo.data.repository.datasource.local

import com.josegrillo.data.entity.FavoriteDTO
import io.reactivex.Completable
import io.reactivex.Single

interface CharacterLocalDataSource {
    fun getCharacterIsFavorite(characterId: Int): Single<Boolean>
    fun insertCharacterAsFavorite(favoriteDTO: FavoriteDTO): Completable
    fun removeCharacterFromFavorite(favoriteDTO: FavoriteDTO): Completable
}