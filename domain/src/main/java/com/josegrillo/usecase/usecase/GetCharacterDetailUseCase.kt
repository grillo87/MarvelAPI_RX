package com.josegrillo.usecase.usecase

import com.josegrillo.usecase.repository.CharactersRepository

class GetCharacterDetailUseCase(private val charactersRepository: CharactersRepository) {
    operator fun invoke(characterId: Int) =
        charactersRepository.getCharacterDetail(characterId)
}