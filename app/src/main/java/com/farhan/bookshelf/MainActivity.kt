package com.farhan.bookshelf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.farhan.bookshelf.ui.screens.BookshelfViewModel
import com.farhan.bookshelf.ui.screens.MainScreen
import com.farhan.bookshelf.ui.theme.BookshelfTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val bookshelfViewModel: BookshelfViewModel by viewModels()
        setContent {
            BookshelfTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        books= bookshelfViewModel.uiState.collectAsState().value.books,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


