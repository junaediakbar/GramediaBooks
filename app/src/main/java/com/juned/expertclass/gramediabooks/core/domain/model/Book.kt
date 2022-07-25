package com.juned.expertclass.gramediabooks.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Book(
    val slug: String,
    val image: String,
    val originalUrl: String,
    val author: String,
    val price: String,
    val title: String,
    val url: String,
    val isFavorite : Boolean
) : Parcelable