package com.josegrillo.usecase

import com.google.gson.Gson
import com.josegrillo.usecase.entity.CharacterBO
import com.josegrillo.usecase.repository.CharactersRepository
import com.josegrillo.usecase.usecase.GetCharacterDetailUseCase
import com.josegrillo.usecase.utils.DataReaderUtils.readFileWithoutNewLineFromResources
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GetCharacterDetailUseCaseTest {
    private lateinit var getCharacterDetailUseCase: GetCharacterDetailUseCase

    @Mock
    private lateinit var charactersRepository: CharactersRepository

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        getCharacterDetailUseCase =
            GetCharacterDetailUseCase(charactersRepository)
    }

    @Test
    fun testGetCharacterDetail() {

        val mockCharacter = Gson().fromJson(
            readFileWithoutNewLineFromResources("character.json"),
            CharacterBO::class.java
        )

        // WHEN
        Mockito.`when`(
            charactersRepository.getCharacterDetail(Mockito.anyInt())
        ).thenReturn(
            Single.just(
                mockCharacter
            )
        )

        // THEN
        val useCaseResult = getCharacterDetailUseCase.invoke(Mockito.anyInt())

        // WHAT
        assert(useCaseResult.blockingGet().id == 10)
        assert(useCaseResult.blockingGet().name == "Test Character")
        assert(useCaseResult.blockingGet().description == "Test Description")
        assert(useCaseResult.blockingGet().thumbnail != null)
        assert(useCaseResult.blockingGet().thumbnail == "https://www.google.com/test.jpg")
    }

}