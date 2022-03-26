package com.josegrillo.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.josegrillo.data.mapper.FavoriteMapper
import com.josegrillo.data.repository.datasource.local.CharacterLocalDataSource
import com.josegrillo.data.repository.datasource.remote.CharacterRemoteDataSource
import com.josegrillo.data.mapper.CharacterMapper
import com.josegrillo.usecase.entity.CharacterBO
import com.josegrillo.usecase.repository.CharactersRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single


class CharactersRepositoryImpl(
    private val characterRemoteDataSource: CharacterRemoteDataSource,
    private val characterLocalDataSource: CharacterLocalDataSource,
    private val favoriteMapper: FavoriteMapper,
    private val characterMapper: CharacterMapper
) : CharactersRepository {

    override fun getCharacters(): Flowable<PagingData<CharacterBO>> {
        return characterRemoteDataSource.getCharacters().map {
            it.map(characterMapper::map)
        }
    }

    override fun getCharacterDetail(characterId: Int): Single<CharacterBO> {
        return characterRemoteDataSource.getCharacterDetail(characterId).map {
            characterMapper.map(it)
        }
    }

    override fun checkFavoriteCharacter(characterId: Int): Single<Boolean> {
        return characterLocalDataSource.getCharacterIsFavorite(characterId)
    }

    override fun insertFavorite(characterId: Int): Completable {
        return characterLocalDataSource.insertCharacterAsFavorite(favoriteMapper.map(characterId))
    }

    override fun deleteFavorite(characterId: Int): Completable {
        return characterLocalDataSource.removeCharacterFromFavorite(favoriteMapper.map(characterId))
    }
}