package com.josegrillo.data.db

import androidx.room.*
import com.josegrillo.data.entity.FavoriteDTO
import io.reactivex.Completable
import io.reactivex.Maybe

@Dao
interface FavoriteDAO {

    @Query("SELECT * FROM ${FavoriteDTO.TABLE_NAME} WHERE ${FavoriteDTO.CHARACTER_ID_COLUMN} = :characterId")
    fun getFavoriteById(characterId: Int): Maybe<List<FavoriteDTO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(favoriteDTO: FavoriteDTO): Completable

    @Delete
    fun delete(favoriteDTO: FavoriteDTO): Completable
}