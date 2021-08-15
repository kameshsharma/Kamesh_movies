package com.example.lowesmovies.backend.datasource

import ccom.example.lowesmovies.models.Result
import  com.example.lowesmovies.backend.network.MovieService
import  com.example.lowesmovies.utils.ErrorUtils
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject
import com.example.lowesmovies.models.*

class RemoteDataSource @Inject constructor(private val retrofit: Retrofit) {

    suspend fun fetchListOfMovies(): ccom.example.lowesmovies.models.Result<MovieData> {
        val movieService = retrofit.create(MovieService::class.java);
        return getResponse(
            request = { movieService.getListOfMovies() },
            defaultErrorMessage = "Error fetching Books list")

    }

    private suspend fun <T> getResponse(request: suspend () -> Response<T>, defaultErrorMessage: String): ccom.example.lowesmovies.models.Result<T> {
        return try {
            val result = request.invoke()
            if (result.isSuccessful) {
                return ccom.example.lowesmovies.models.Result.success(result.body())
            } else {
                val errorResponse = ErrorUtils.parseError(result, retrofit)
                ccom.example.lowesmovies.models.Result.error(errorResponse?.status_message ?: defaultErrorMessage, errorResponse)
            }
        } catch (e: Throwable) {
            Result.error("Unknown Error", null)
        }
    }
}