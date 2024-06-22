package com.farhan.bookshelf.model

import com.google.gson.annotations.SerializedName

data class BookResponse(
    val kind: String,
    val totalItems: Int,
    val items: List<BookItem>
)

data class BookItem(
    val kind: String,
    val id: String,
    val volumeInfo: VolumeInfo,

)
data class VolumeInfo(
    val title: String
)
