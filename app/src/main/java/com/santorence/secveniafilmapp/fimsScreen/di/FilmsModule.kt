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

val filmsModule = module {
    single<FilmsRepository> {
        return@single FilmsRepositoryImpl(get())
    }
    single { FilmsRemoteDataSource(createFilmApi(get())) }
    single { GetFilmsUseCase(get()) }

    viewModel {
        FilmsViewModel(getFilmsUseCase = get())
    }
}

private fun createFilmApi(retrofit: Retrofit): FilmsApi {
    return retrofit.create(FilmsApi::class.java)
}