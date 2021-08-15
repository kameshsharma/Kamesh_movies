package com.example.lowesmovies.utils

import ccom.example.lowesmovies.models.Error
import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException

object ErrorUtils {

    fun parseError(response: Response<*>, retrofit: Retrofit): ccom.example.lowesmovies.models.Error? {
        val converter = retrofit.responseBodyConverter<Error>(ccom.example.lowesmovies.models.Error::class.java, arrayOfNulls(0))
        return try {
            converter.convert(response.errorBody()!!)
        } catch (e: IOException) {
            ccom.example.lowesmovies.models.Error()
        }
    }
}