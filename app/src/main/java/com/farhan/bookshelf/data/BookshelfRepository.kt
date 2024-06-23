package com.farhan.bookshelf.data

import com.farhan.bookshelf.model.BookResponse
import com.farhan.bookshelf.network.BookshelfApi
import retrofit2.Call

interface BookshelfRepository {
    fun getBooks(): Call<BookResponse>

}


class NetworkBookshelfRepository: BookshelfRepository{
    override fun getBooks(): Call<BookResponse> {
        return BookshelfApi.retrofitService.getBooks()
    }
}
