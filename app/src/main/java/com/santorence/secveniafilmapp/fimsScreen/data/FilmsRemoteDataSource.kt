package com.santorence.secveniafilmapp.fimsScreen.data

import com.santorence.secveniafilmapp.fimsScreen.data.dto.FilmDto

class FilmsRemoteDataSource (private val filmsApi: FilmsApi ) {

    suspend fun getFilms() : List<FilmDto> {
        return filmsApi.getFilms().films
    }

}