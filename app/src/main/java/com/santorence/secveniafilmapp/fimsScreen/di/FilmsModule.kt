package com.santorence.secveniafilmapp.fimsScreen.di

import com.santorence.secveniafilmapp.fimsScreen.data.FilmsApi
import com.santorence.secveniafilmapp.fimsScreen.data.FilmsRemoteDataSource
import com.santorence.secveniafilmapp.fimsScreen.data.FilmsRepositoryImpl
import com.santorence.secveniafilmapp.fimsScreen.domain.FilmsRepository
import com.santorence.secveniafilmapp.fimsScreen.domain.GetFilmsUseCase
import com.santorence.secveniafilmapp.fimsScreen.presentation.FilmsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val filmsModule = module {
    single<FilmsRepository> {
        return@single FilmsRepositoryImpl(get())
    }
    single { FilmsRemoteDataSource(createFilmApi()) }
    single { GetFilmsUseCase(get()) }

    viewModel {
        FilmsViewModel(getFilmsUseCase = get())
    }
}

private const val url = "https://s3-eu-west-1.amazonaws.com"
private fun createFilmApi(): FilmsApi {
    return Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(FilmsApi::class.java)
}