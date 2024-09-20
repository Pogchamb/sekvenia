package com.santorence.secveniafilmapp.fimsScreen.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santorence.secveniafilmapp.fimsScreen.domain.GetFilmsUseCase
import com.santorence.secveniafilmapp.fimsScreen.domain.model.FilmModel
import com.santorence.secveniafilmapp.utils.GenreItem
import kotlinx.coroutines.launch

class FilmsViewModel(
    private val getFilmsUseCase: GetFilmsUseCase
): ViewModel() {
    private val _filmsLiveData: MutableLiveData<MutableList<FilmModel>> = MutableLiveData()
    val filmsLiveData: LiveData<MutableList<FilmModel>>
        get() = _filmsLiveData

    private val _genreLiveData: MutableLiveData<GenreItem> = MutableLiveData()
    val genreLiveData: LiveData<GenreItem>
        get() = _genreLiveData

    fun fetchFilms() {
        viewModelScope.launch {
            try {
                val films = getFilmsUseCase()
                _filmsLiveData.postValue(films.toMutableList())
            } catch (e: Exception) {
                //TODO ошибки обработать
            }
        }
    }

    fun filterFilmsByGenre(genreItem: GenreItem) {
        viewModelScope.launch {
            try {
                _genreLiveData.postValue(genreItem)
            } catch (e: Exception) {

            }
        }
    }
}