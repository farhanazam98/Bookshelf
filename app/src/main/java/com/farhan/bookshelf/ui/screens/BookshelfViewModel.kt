package com.farhan.bookshelf.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farhan.bookshelf.data.NetworkBookshelfRepository
import com.farhan.bookshelf.model.BookResponse
import com.farhan.bookshelf.model.BookListResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

data class BookshelfUiState(val bookImageUrls: List<String> = listOf())


class BookshelfViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(BookshelfUiState())
    val uiState = _uiState.asStateFlow()

    private val bookshelfRepository = NetworkBookshelfRepository()

    init {
        viewModelScope.launch {
            val booksResponseCall = bookshelfRepository.getBooks()

            booksResponseCall.enqueue(
                object : Callback<BookListResponse> {
                    override fun onResponse(call: Call<BookListResponse>, response: Response<BookListResponse>) {
                        val books  = response.body()?.items
                        if (books != null) {
                            fetchIndividualBooks(books)
                        }
                    }
                    override fun onFailure(call: Call<BookListResponse>, throwable: Throwable) {

                    }

                }
            )
        }

    }


    private fun fetchIndividualBooks(books: List<BookResponse>?) {
        viewModelScope.launch {
            val bookImageUrls: ArrayList<String> = arrayListOf()
            withContext(Dispatchers.IO) {
                books?.forEach { bookItem ->
                    val bookCall = bookshelfRepository.getBook(bookItem.id)
                    try {
                        val response = bookCall.execute()
                        if (response.isSuccessful) {
                            response.body()?.volumeInfo?.imageLinks?.medium?.let {
                                bookImageUrls.add(it)
                            }
                        } else {

                            println("Error: ${response.code()} ${response.message()}")
                        }
                    } catch (e: IOException) {

                        println("Network error: ${e.message}")
                    }

                }
                _uiState.update { it.copy(bookImageUrls = bookImageUrls) }

            }
        }
    }

    private companion object{
        const val TAG = "BookshelfViewModel"
    }

}
