package com.santorence.secveniafilmapp.fimsScreen.data

import com.santorence.secveniafilmapp.fimsScreen.data.extension.toModel
import com.santorence.secveniafilmapp.fimsScreen.domain.FilmsRepository
import com.santorence.secveniafilmapp.fimsScreen.domain.model.FilmModel

class FilmsRepositoryImpl(
    private val remoteDataSource: FilmsRemoteDataSource
) : FilmsRepository {

    override suspend fun getFilms(): List<FilmModel> {
        return try {
            remoteDataSource.getFilms().map {
                it.toModel()
            }
        } catch (e: Exception) {
            //TODO добавить обработку ошибок
            emptyList()
        }
    }
}