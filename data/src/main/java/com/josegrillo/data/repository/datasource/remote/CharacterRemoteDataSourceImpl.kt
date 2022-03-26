package com.josegrillo.data.repository.datasource.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.flowable
import com.josegrillo.data.api.MarvelApi
import com.josegrillo.data.paging.CharacterPagingSource
import com.josegrillo.data.entity.CharacterDTO
import io.reactivex.Flowable
import io.reactivex.Single

class CharacterRemoteDataSourceImpl(
    private val characterPagingSource: CharacterPagingSource,
    private val marvelApi: MarvelApi
) :
    CharacterRemoteDataSource {

    @OptIn(ExperimentalPagingApi::class)
    override fun getCharacters(): Flowable<PagingData<CharacterDTO>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                initialLoadSize = 40

            ),
            pagingSourceFactory = { characterPagingSource }
        ).flowable
    }

    override fun getCharacterDetail(characterId: Int): Single<CharacterDTO> {
        return marvelApi.getCharacterDetail(characterId)
            .map {
                return@map it.dataDTO.characterDTOS.first()
            }
    }

}