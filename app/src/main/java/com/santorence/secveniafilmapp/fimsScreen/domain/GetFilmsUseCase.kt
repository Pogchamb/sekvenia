package com.santorence.secveniafilmapp.fimsScreen.domain

import com.santorence.secveniafilmapp.fimsScreen.domain.model.FilmModel

class GetFilmsUseCase(private val filmsRepository: FilmsRepository) {

    suspend operator fun invoke(): List<FilmModel> {
        return filmsRepository.getFilms()
    }

}