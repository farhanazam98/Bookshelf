package com.farhan.bookshelf.network

import com.farhan.bookshelf.BuildConfig
import com.farhan.bookshelf.model.BookResponse
import com.farhan.bookshelf.model.BookListResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

// Add logging
val logging = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}
val client = OkHttpClient.Builder()
    .addInterceptor(logging)
    .build()
private val retrofit = Retrofit.Builder()
    .baseUrl("https://www.googleapis.com/books/v1/").addConverterFactory(GsonConverterFactory.create())
    .client(client)
    .build()


private const val API_KEY = BuildConfig.API_KEY

interface BookshelfApiService {
    @GET("volumes/?q=jazz+history&key=$API_KEY")
     fun getBooks(): Call<BookListResponse>

    @GET("volumes/{id}?key=$API_KEY")
    fun getBook(@Path("id") bookId: String): Call<BookResponse>

}

object BookshelfApi {
    val retrofitService: BookshelfApiService by lazy {
        retrofit.create(BookshelfApiService::class.java)
    }
}
