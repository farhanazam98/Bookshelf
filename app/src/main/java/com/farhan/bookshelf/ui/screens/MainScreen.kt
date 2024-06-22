package com.farhan.bookshelf.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.farhan.bookshelf.ui.theme.BookshelfTheme


@Composable
fun MainScreen(modifier: Modifier = Modifier, books : List<String>){
    LazyColumn(modifier = modifier) {
        items(books){
            it: String -> BookName(name = it)
        }
    }
}

@Composable
fun BookName(name: String, modifier: Modifier = Modifier) {
    Text(
        text = name,
        style = MaterialTheme.typography.headlineLarge,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BookshelfTheme {
        BookName("Android")
    }
}
