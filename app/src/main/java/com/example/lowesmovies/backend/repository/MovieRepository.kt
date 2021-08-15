package  com.example.lowesmovies.backend.repository

import ccom.example.lowesmovies.models.Result
import  com.example.lowesmovies.backend.datasource.RemoteDataSource
import  com.example.lowesmovies.models.MovieData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieRepository @Inject constructor(private val movieDataSource: RemoteDataSource){

    suspend fun fetchMovies() : Flow<Result<MovieData?>?>{
        return flow{
            emit(ccom.example.lowesmovies.models.Result.loading())
            val result = movieDataSource.fetchListOfMovies()
            emit(result)

        }.flowOn(Dispatchers.IO)

    }

}