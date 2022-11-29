package com.technipixl.evand.movie2.Network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class BaseApiService {
    val BASE_URL = "https://api.themoviedb.org/3/"
    val APIKEY = "b2168bae3a2c67509eb6b97572f521c2"
//	private val BASE_IMG_URL = "https://image.tmdb.org/t/p/w500/"

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}