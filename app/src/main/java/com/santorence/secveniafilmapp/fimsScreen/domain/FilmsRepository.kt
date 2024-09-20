package com.santorence.secveniafilmapp.fimsScreen.domain

import com.santorence.secveniafilmapp.fimsScreen.domain.model.FilmModel

interface FilmsRepository {
    suspend fun getFilms(): List<FilmModel>
}