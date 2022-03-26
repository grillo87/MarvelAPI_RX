package com.josegrillo.data.repository.datasource.remote

import androidx.paging.PagingData
import com.josegrillo.data.entity.CharacterDTO
import io.reactivex.Flowable
import io.reactivex.Single

interface CharacterRemoteDataSource {
    fun getCharacters(): Flowable<PagingData<CharacterDTO>>
    fun getCharacterDetail(characterId: Int): Single<CharacterDTO>
}