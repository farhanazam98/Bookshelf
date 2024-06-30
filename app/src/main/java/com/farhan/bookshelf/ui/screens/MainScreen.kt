package com.farhan.bookshelf.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.farhan.bookshelf.R
import com.farhan.bookshelf.model.ImageLinks
import com.farhan.bookshelf.model.VolumeInfo
import com.farhan.bookshelf.ui.theme.BookshelfTheme


@Composable
fun MainScreen(modifier: Modifier = Modifier, bookImageUrls: List<String>) {
    LazyColumn(modifier = modifier) {
        items(bookImageUrls) { it: String ->
//            BookCard(bookInfo = it)
            Book(it)
        }
    }
}

@Composable
fun Book(imageUrl: String,
         @DrawableRes placeholder: Int = R.drawable.loading_img,
) {
    val formattedUrl = imageUrl.replace("http", "https")
    AsyncImage(
        model = formattedUrl,
        contentDescription = null,
        placeholder = painterResource(placeholder)
    )
}

@Composable
fun BookCard(
    bookInfo: VolumeInfo,
    @DrawableRes placeholder: Int = R.drawable.loading_img,
) {
    val authorsString = bookInfo.authors.joinToString(", \n")
    // remove assertion
    val resolvedUrl: String =
        if (bookInfo.imageLinks != null) bookInfo.imageLinks.thumbnail?.replace(
            "http",
            "https"
        )!! else "https://fakeimg.pl/200x250?text=No+cover&font=bebas"
    Card {
        Row(
            modifier = Modifier.padding(8.dp).fillMaxWidth(),
        ) {
            AsyncImage(
                model = resolvedUrl,
                contentDescription = null,
                placeholder = painterResource(placeholder)
            )
            Column(modifier = Modifier.padding(all = 4.dp)){
                Text(
                    text = "Title:\n${bookInfo.title}",
                    style = MaterialTheme.typography.labelMedium,
                )
                Text(
                    text = "Author(s): \n$authorsString",
                    style = MaterialTheme.typography.labelSmall,
                )
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun BookCardPreview() {
    val imageLinks = ImageLinks(thumbnail = PLACEHOLDER_URL)
    val bookInfo = VolumeInfo(
        title = "Moby Dick",
        authors = listOf("Herman Melville"),
        imageLinks = imageLinks
    )
    BookshelfTheme {
        BookCard(bookInfo = bookInfo, placeholder = R.drawable.moby_dick)
    }
}

const val PLACEHOLDER_URL = "https://fakeimg.pl/200x250?text=No+cover&font=bebas"
