package com.farhan.bookshelf.network

import com.farhan.bookshelf.model.BookResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


private val retrofit = Retrofit.Builder()
    .baseUrl("https://www.googleapis.com/books/v1/").addConverterFactory(GsonConverterFactory.create())
    .build()


interface BookshelfApiService {
    @GET("volumes/?q=jazz+history&key=AIzaSyDQRg4v4o1hNG95Iy9BO1n1nFdA_NqMefs")
     fun getBooks(): Call<BookResponse>
}

object BookshelfApi {
    val retrofitService: BookshelfApiService by lazy {
        retrofit.create(BookshelfApiService::class.java)
    }
}
