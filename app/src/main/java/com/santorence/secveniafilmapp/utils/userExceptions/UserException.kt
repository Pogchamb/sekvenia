package com.santorence.secveniafilmapp.utils.userExceptions

sealed interface UserException {
    val errorMessage: Int
}