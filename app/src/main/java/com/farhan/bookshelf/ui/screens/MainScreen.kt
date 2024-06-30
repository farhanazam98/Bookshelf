package com.farhan.bookshelf.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
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
fun MainScreen(modifier: Modifier = Modifier, bookImageUrls: List<String>, @DrawableRes placeholder: Int =  R.drawable.loading_img) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = modifier) {
        items(bookImageUrls) { it: String ->
            Book(it, placeholder)
        }
    }
}

@Composable
fun Book(imageUrl: String,
         @DrawableRes placeholder: Int,
) {
    val formattedUrl = imageUrl.replace("http", "https")
    AsyncImage(
        model = formattedUrl,
        contentDescription = null,
        placeholder = painterResource(placeholder),
        contentScale = ContentScale.FillWidth

    )
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview(){
    val bookImageUrls: ArrayList<String>  = arrayListOf()
     for (num in 0..7){
         bookImageUrls.add("https://example.com")
    }
    MainScreen(bookImageUrls = bookImageUrls, placeholder = R.drawable.moby_dick)
}


const val PLACEHOLDER_URL = "https://fakeimg.pl/200x250?text=No+cover&font=bebas"
