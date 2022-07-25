package com.juned.expertclass.gramediabooks.core.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "book")
data class BookEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "slug")
    val slug: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "image")
    var image: String,

    @ColumnInfo(name = "original_url")
    var originalUrl: String,

    @ColumnInfo(name = "author")
    val author: String,

    @ColumnInfo(name = "price")
    var price: String,

    @ColumnInfo(name = "url")
    var url: String,

    @ColumnInfo(name = "url")
    var url: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
) : Parcelable
