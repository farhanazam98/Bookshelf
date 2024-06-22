package com.farhan.bookshelf.network

import com.farhan.bookshelf.BuildConfig
import com.farhan.bookshelf.model.BookResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


private val retrofit = Retrofit.Builder()
    .baseUrl("https://www.googleapis.com/books/v1/").addConverterFactory(GsonConverterFactory.create())
    .build()


private const val API_KEY = BuildConfig.API_KEY
interface BookshelfApiService {
    @GET("volumes/?q=jazz+history&key=$API_KEY")
     fun getBooks(): Call<BookResponse>
}

object BookshelfApi {
    val retrofitService: BookshelfApiService by lazy {
        retrofit.create(BookshelfApiService::class.java)
    }
}
