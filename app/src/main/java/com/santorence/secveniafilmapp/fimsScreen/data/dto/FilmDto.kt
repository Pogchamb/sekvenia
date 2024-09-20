package com.santorence.secveniafilmapp.fimsScreen.data.dto

import com.google.gson.annotations.SerializedName

data class FilmDto(
    val id: Int,
    @SerializedName("localized_name")
    val localizedName: String,
    val year: Int,
    val rating: Double,
    @SerializedName("image_url")
    val imageUrl: String,
    val description: String,
    val genres: List<String>
)
