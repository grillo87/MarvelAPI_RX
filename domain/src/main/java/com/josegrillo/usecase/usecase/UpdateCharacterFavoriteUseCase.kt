package com.josegrillo.usecase.usecase

import com.josegrillo.usecase.repository.CharactersRepository

class UpdateCharacterFavoriteUseCase(private val charactersRepository: CharactersRepository) {
    operator fun invoke(characterId: Int) =
        charactersRepository.checkFavoriteCharacter(characterId).flatMapCompletable {
            if (it) {
                charactersRepository.deleteFavorite(characterId)
            } else {
                charactersRepository.insertFavorite(characterId)
            }
        }
}