package com.farhan.bookshelf.ui.screens

import android.util.Log
import android.widget.ArrayAdapter
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farhan.bookshelf.model.BookResponse
import com.farhan.bookshelf.network.BookshelfApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

data class BookshelfUiState(val books: List<String> = listOf(), val state: State)

enum class State {
    LOADING,
    LOADED,
}



class BookshelfViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(BookshelfUiState(state = State.LOADING))
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update { it.copy(books = listOf("Moby Dick", "Tom Sawyer")) }
        viewModelScope.launch {
            val booksResponseCall = BookshelfApi.retrofitService.getBooks()
            booksResponseCall.enqueue(
                object : Callback<BookResponse> {
                    override fun onResponse(p0: Call<BookResponse>, p1: Response<BookResponse>) {
                        Log.d("BookShelfViewModel", "made it")
                        Log.d("BookshelfViewModel", p1.toString())
                        val bookNames: MutableList<String> = mutableListOf()
                        p1.body()?.items?.forEach { bookItem ->
                            bookItem.volumeInfo.title.let { title ->
                                bookNames.add(title)
                            }
                            Log.d("BookShelfViewModel", "made it 2")

                        }
                        if (bookNames.isNotEmpty()) {
                            Log.d("BookShelfViewModel", "made it 3")

                            _uiState.update { it.copy(books = bookNames) }
                        }
                    }

                    override fun onFailure(p0: Call<BookResponse>, p1: Throwable) {

                    }

                }
            )
        }

    }

}
