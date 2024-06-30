package com.farhan.bookshelf.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.farhan.bookshelf.R


@Composable
fun ResultsScreen(modifier: Modifier = Modifier, bookImageUrls: List<String>, @DrawableRes placeholder: Int =  R.drawable.loading_img) {
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
    ResultsScreen(bookImageUrls = bookImageUrls, placeholder = R.drawable.moby_dick)
}
