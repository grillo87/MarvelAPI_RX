package com.josegrillo.data.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import com.josegrillo.data.entity.CharacterResultDTO
import io.reactivex.Single

interface MarvelApi {
    @GET("characters")
    fun getCharacters(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int = 20,
        @Query("orderBy") orderBy: String = "name"
    ): Single<CharacterResultDTO>

    @GET("characters/{characterId}")
    fun getCharacterDetail(
        @Path("characterId") characterId: Int
    ): Single<CharacterResultDTO>
}