package com.example.movieapp.service.repository

import com.example.movieapp.service.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun getMoviesByTitle(@Url url:String): Response<MovieResponse>
}