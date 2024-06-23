package com.farhan.bookshelf.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.farhan.bookshelf.model.BookItem
import com.farhan.bookshelf.model.VolumeInfo
import com.farhan.bookshelf.ui.theme.BookshelfTheme


@Composable
fun MainScreen(modifier: Modifier = Modifier, books : List<VolumeInfo>){
    LazyColumn(modifier = modifier) {
        items(books){
            it: VolumeInfo -> BookCard(bookInfo = it)
        }
    }
}

@Composable
fun BookCard(bookInfo: VolumeInfo, modifier: Modifier = Modifier) {
    val authorsString = bookInfo.authors.joinToString(", \n")
    val resolvedUrl = if (bookInfo.imageLinks != null) bookInfo.imageLinks.thumbnail.replace("http", "https") else "https://fakeimg.pl/200x250?text=No+cover&font=bebas"
    Card {
        Column(modifier = modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            AsyncImage(model = resolvedUrl, contentDescription = null)
            Text(
                text = "Title: ${bookInfo.title}",
                style = MaterialTheme.typography.labelMedium,
            )
            Text(
                text = "Author(s): ${authorsString}",
                style = MaterialTheme.typography.labelSmall,
                textAlign = TextAlign.Center

            )
        }
    }

}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BookshelfTheme {
    }
}
