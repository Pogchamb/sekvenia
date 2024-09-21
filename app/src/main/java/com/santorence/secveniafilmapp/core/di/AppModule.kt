package com.santorence.secveniafilmapp.core.di

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val appModule = module {

    single { provideRetrofit() }
}
private const val url = "https://s3-eu-west-1.amazonaws.com"
private fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

