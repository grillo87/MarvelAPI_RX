package com.josegrillo.data.repository.datasource.local


import com.josegrillo.data.db.FavoriteDAO
import com.josegrillo.data.entity.FavoriteDTO
import io.reactivex.Completable
import io.reactivex.Single

class CharacterLocalDataSourceImpl(private val favoriteDAO: FavoriteDAO) :
    CharacterLocalDataSource {

    override fun getCharacterIsFavorite(characterId: Int): Single<Boolean> {
        return favoriteDAO.getFavoriteById(characterId).map {
            it.isNotEmpty()
        }.switchIfEmpty(Single.just(false))
    }

    override fun insertCharacterAsFavorite(favoriteDTO: FavoriteDTO): Completable {
        return favoriteDAO.insert(favoriteDTO)
    }

    override fun removeCharacterFromFavorite(favoriteDTO: FavoriteDTO): Completable {
        return favoriteDAO.delete(favoriteDTO)
    }
}