package com.santorence.secveniafilmapp.fimsScreen.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santorence.secveniafilmapp.fimsScreen.domain.GetFilmsUseCase
import com.santorence.secveniafilmapp.fimsScreen.domain.model.FilmModel
import com.santorence.secveniafilmapp.fimsScreen.domain.model.GenreModel
import com.santorence.secveniafilmapp.utils.userExceptions.ConnectionException
import com.santorence.secveniafilmapp.utils.userExceptions.UserException
import kotlinx.coroutines.launch

class FilmsViewModel(
    private val getFilmsUseCase: GetFilmsUseCase,
) : ViewModel() {
    private val _filmsLiveData: MutableLiveData<MutableList<FilmModel>> = MutableLiveData()
    val filmsLiveData: LiveData<MutableList<FilmModel>>
        get() = _filmsLiveData

    private val _exceptionLiveData: MutableLiveData<UserException> = MutableLiveData()
    val exceptionLiveData: LiveData<UserException>
        get() = _exceptionLiveData

    private val _genreLiveData: MutableLiveData<GenreModel> = MutableLiveData()
    val genreLiveData: LiveData<GenreModel>
        get() = _genreLiveData

    fun fetchFilms() {
        viewModelScope.launch {
            try {
                val films = getFilmsUseCase()
                _filmsLiveData.postValue(films.toMutableList())
            } catch (e: ConnectionException) {
                _exceptionLiveData.postValue(e)
            }
        }
    }

    fun filterFilmsByGenre(genreModel: GenreModel) {
        viewModelScope.launch {
            try {
                _genreLiveData.postValue(genreModel)
            } catch (_: Exception) {

            }
        }
    }

}