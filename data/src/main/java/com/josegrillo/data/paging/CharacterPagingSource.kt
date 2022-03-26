package com.josegrillo.data.paging

import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import com.josegrillo.data.api.MarvelApi
import com.josegrillo.data.entity.CharacterDTO
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class CharacterPagingSource(
    private val marvelApi: MarvelApi
) : RxPagingSource<Int, CharacterDTO>() {

    private val networkPageSize = 20
    private val initialLoadSize = 1

    override fun getRefreshKey(state: PagingState<Int, CharacterDTO>): Int? {
        return null
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, CharacterDTO>> {
        val position = params.key ?: initialLoadSize
        val offset =
            if (params.key != null) ((position - 1) * networkPageSize) + 1 else initialLoadSize
        return marvelApi
            .getCharacters(offset, networkPageSize)
            .subscribeOn(Schedulers.io())
            .map {
                toLoadResult(params, position, it.dataDTO.characterDTOS)
            }
            .onErrorReturn {
                LoadResult.Error(it)
            }
    }

    private fun toLoadResult(
        params: LoadParams<Int>,
        position: Int,
        charactersDTO: List<CharacterDTO>
    ): LoadResult<Int, CharacterDTO> {
        return LoadResult.Page(
            data = charactersDTO,
            prevKey = null,
            nextKey = if (charactersDTO.isEmpty()) {
                null
            } else {
                position + (params.loadSize / networkPageSize)
            }

        )
    }
}