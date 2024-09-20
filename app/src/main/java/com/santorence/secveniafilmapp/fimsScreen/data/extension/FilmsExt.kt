package com.santorence.secveniafilmapp.fimsScreen.data.extension

import com.santorence.secveniafilmapp.fimsScreen.data.dto.FilmDto
import com.santorence.secveniafilmapp.fimsScreen.domain.model.FilmModel

fun FilmDto.toModel(): FilmModel = FilmModel(
    id, localizedName, year, rating, imageUrl, description, genres
)