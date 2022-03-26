package com.josegrillo.usecase.usecase

import com.josegrillo.usecase.repository.CharactersRepository

class GetCharacterIsFavoriteUseCase(private val charactersRepository: CharactersRepository) {
    operator fun invoke(characterId: Int) =
        charactersRepository.checkFavoriteCharacter(characterId)
}