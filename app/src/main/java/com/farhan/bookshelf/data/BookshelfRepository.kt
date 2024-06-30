package com.farhan.bookshelf.data

import com.farhan.bookshelf.model.BookResponse
import com.farhan.bookshelf.model.BookListResponse
import com.farhan.bookshelf.network.BookshelfApi
import retrofit2.Call

interface BookshelfRepository {
    fun getBooks(searchQuery: String): Call<BookListResponse>
    fun getBook(bookId: String): Call<BookResponse>

}


class NetworkBookshelfRepository: BookshelfRepository{
    override fun getBooks(searchQuery: String): Call<BookListResponse> {
        return BookshelfApi.retrofitService.getBooks(searchQuery)
    }

    override fun getBook(bookId: String): Call<BookResponse> {
        return BookshelfApi.retrofitService.getBook(bookId)
    }
}
