package com.josegrillo.data

import com.google.gson.Gson
import com.josegrillo.data.mapper.FavoriteMapper
import com.josegrillo.data.repository.CharactersRepositoryImpl
import com.josegrillo.data.repository.datasource.local.CharacterLocalDataSource
import com.josegrillo.data.repository.datasource.remote.CharacterRemoteDataSource
import com.josegrillo.data.utils.DataUtils.readFileWithoutNewLineFromResources
import com.josegrillo.data.entity.CharacterDTO
import com.josegrillo.data.mapper.CharacterMapper
import com.josegrillo.usecase.entity.CharacterBO
import junit.framework.TestCase
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.Mockito
import io.reactivex.Single

class CharactersRepositoryImplTest : TestCase() {

    private lateinit var charactersRepository: CharactersRepositoryImpl
    private val characterRemoteDataSource: CharacterRemoteDataSource = mock()
    private val characterLocalDataSource: CharacterLocalDataSource = mock()
    private val favoriteMapper: FavoriteMapper = mock()
    private val characterMapper: CharacterMapper = mock()

    @Test
    fun testGetCharacterDetail() {

        charactersRepository = CharactersRepositoryImpl(
            characterRemoteDataSource,
            characterLocalDataSource,
            favoriteMapper,
            characterMapper
        )

        val mockCharacterDTO = Gson().fromJson(
            readFileWithoutNewLineFromResources("character_dto.json"),
            CharacterDTO::class.java
        )

        val mockCharacterBO = Gson().fromJson(
            readFileWithoutNewLineFromResources("character_bo.json"),
            CharacterBO::class.java
        )

        Mockito.`when`((characterRemoteDataSource.getCharacterDetail(Mockito.anyInt())))
            .thenReturn(
                Single.just(mockCharacterDTO)
            )

        Mockito.`when`((characterMapper.map(mockCharacterDTO)))
            .thenReturn(
                mockCharacterBO
            )

        val characterResult = charactersRepository.getCharacterDetail(Mockito.anyInt())
        assert(characterResult.blockingGet().id == 10)
        assert(characterResult.blockingGet().name == "Test Character")
        assert(characterResult.blockingGet().description == "Test Description")
        assert(characterResult.blockingGet().thumbnail != null)
        assert(characterResult.blockingGet().thumbnail == "https://www.google.com/test.jpg")

    }
}