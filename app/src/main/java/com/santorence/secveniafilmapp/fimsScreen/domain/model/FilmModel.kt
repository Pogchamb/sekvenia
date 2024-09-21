package com.santorence.secveniafilmapp.fimsScreen.domain.model

import java.io.Serializable

data class FilmModel(
    val id: Int,
    val localizedName: String?,
    val year: Int?,
    val rating: Double?,
    val imageUrl: String?,
    val description: String?,
    val genres: List<String?>
) : Serializable
