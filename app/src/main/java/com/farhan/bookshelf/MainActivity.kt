package com.farhan.bookshelf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import com.farhan.bookshelf.ui.screens.BookshelfViewModel
import com.farhan.bookshelf.ui.screens.SearchScreen
import com.farhan.bookshelf.ui.theme.BookshelfTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val bookshelfViewModel: BookshelfViewModel by viewModels()
        setContent {
            BookshelfTheme {
                Scaffold(topBar = {
                    TopAppBar(
                        title = {
                            Text("Bookshelf")
                        }
                    )
                }, modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SearchScreen(modifier= Modifier.padding(innerPadding))
//                    ResultsScreen(
//                        bookImageUrls = bookshelfViewModel.uiState.collectAsState().value.bookImageUrls,
//                        modifier = Modifier.padding(innerPadding)
//                    )
                }
            }
        }
    }
}


