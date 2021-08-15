package  com.example.lowesmovies.backend.network

import  com.example.lowesmovies.models.MovieData
import retrofit2.Response
import retrofit2.http.GET

interface MovieService {

    @GET("search.json?query=%22%22&api-key=ZSe3BUZ5CIWsbkotDOIj7k6pxD0AfHiW")
    suspend fun getListOfMovies() : Response<MovieData>

}