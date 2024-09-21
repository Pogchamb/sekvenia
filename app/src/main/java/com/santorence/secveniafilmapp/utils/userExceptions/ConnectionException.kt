package com.santorence.secveniafilmapp.utils.userExceptions

import com.santorence.secveniafilmapp.R


object ConnectionException : Throwable(), UserException {
    override val errorMessage: Int
        get() = R.string.Connection_Exception
}