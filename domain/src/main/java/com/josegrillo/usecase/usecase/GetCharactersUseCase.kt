package com.josegrillo.usecase.usecase

import androidx.paging.rxjava2.cachedIn
import com.josegrillo.usecase.repository.CharactersRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi

class GetCharactersUseCase(private val charactersRepository: CharactersRepository) {
    @ExperimentalCoroutinesApi
    operator fun invoke(scope: CoroutineScope) =
        charactersRepository.getCharacters().toObservable().cachedIn(scope)
}