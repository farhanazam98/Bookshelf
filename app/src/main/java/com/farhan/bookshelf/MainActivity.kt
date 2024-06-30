package com.farhan.bookshelf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.farhan.bookshelf.ui.screens.BookSearchState
import com.farhan.bookshelf.ui.screens.BookshelfViewModel
import com.farhan.bookshelf.ui.screens.LoadingScreen
import com.farhan.bookshelf.ui.screens.ResultsScreen
import com.farhan.bookshelf.ui.screens.SearchScreen
import com.farhan.bookshelf.ui.theme.BookshelfTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val bookshelfViewModel: BookshelfViewModel by viewModels()
            val uiState by bookshelfViewModel.uiState.collectAsState()
            BookshelfTheme {
                Scaffold(topBar = {
                    TopAppBar(
                        title = {
                            Text("Bookshelf")
                        }
                    )
                }, modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)){
                        when (uiState.state){
                            is BookSearchState.Initial -> SearchScreen { searchQuery: String ->
                                bookshelfViewModel.fetchBooks(searchQuery)
                            }
                            BookSearchState.Empty -> TODO()
                            is BookSearchState.Error -> TODO()
                            is BookSearchState.Loaded -> ResultsScreen(bookImageUrls = uiState.bookImageUrls)
                            BookSearchState.Loading -> LoadingScreen()
                        }

                    }
                }
            }
        }
    }
}


