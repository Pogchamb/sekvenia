package com.santorence.secveniafilmapp.fimsScreen.data

import com.santorence.secveniafilmapp.fimsScreen.data.dto.FilmsDto
import retrofit2.http.GET

interface FilmsApi {
    @GET("/sequeniatesttask/films.json")
    suspend fun getFilms(): FilmsDto

}