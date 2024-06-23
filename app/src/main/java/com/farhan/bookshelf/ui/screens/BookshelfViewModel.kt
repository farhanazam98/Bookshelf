package com.farhan.bookshelf.ui.screens

import android.util.Log
import android.widget.ArrayAdapter
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farhan.bookshelf.data.NetworkBookshelfRepository
import com.farhan.bookshelf.model.BookResponse
import com.farhan.bookshelf.model.VolumeInfo
import com.farhan.bookshelf.network.BookshelfApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

data class BookshelfUiState(val books: List<VolumeInfo> = listOf())


class BookshelfViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(BookshelfUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val booksResponseCall = NetworkBookshelfRepository().getBooks()
            booksResponseCall.enqueue(
                object : Callback<BookResponse> {
                    override fun onResponse(call: Call<BookResponse>, response: Response<BookResponse>) {
                        val books  = response.body()?.items?.map { it.volumeInfo }
                        if (books != null) {
                            _uiState.update { it.copy(books = books)}
                        }
                    }
                    override fun onFailure(call: Call<BookResponse>, throwable: Throwable) {

                    }

                }
            )
        }

    }

}
