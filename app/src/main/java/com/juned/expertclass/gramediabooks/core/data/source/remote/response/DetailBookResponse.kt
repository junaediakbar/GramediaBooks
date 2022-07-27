package com.juned.expertclass.gramediabooks.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailBookResponse(

	@field:SerializedName("book")
	val book: BookDetail,

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("status")
	val status: Int
)

data class BookDetail(

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("original_url")
	val originalUrl: String,

	@field:SerializedName("author")
	val author: String,

	@field:SerializedName("price")
	val price: String,

	@field:SerializedName("detail")
	val detail: Detail,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("url")
	val url: String,

	@field:SerializedName("slug")
	val slug: String
)

data class Detail(

	@field:SerializedName("country")
	val country: String,

	@field:SerializedName("release_date")
	val releaseDate: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("publisher")
	val publisher: String,

	@field:SerializedName("language")
	val language: String,

	@field:SerializedName("category")
	val category: String,

	@field:SerializedName("page_count")
	val pageCount: Int
)
