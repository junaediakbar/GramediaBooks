package com.juned.expertclass.gramediabooks.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListBookResponse(

	@field:SerializedName("books")
	val books: List<BookResponse>,

	@field:SerializedName("meta")
	val meta: Meta,

	@field:SerializedName("links")
	val links: Links,

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("status")
	val status: Int
)

data class Meta(

	@field:SerializedName("path")
	val path: String,

	@field:SerializedName("per_page")
	val perPage: Int,

	@field:SerializedName("from")
	val from: Int,

	@field:SerializedName("to")
	val to: Int,

	@field:SerializedName("current_page")
	val currentPage: Int
)

data class BookResponse(

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("original_url")
	val originalUrl: String,

	@field:SerializedName("author")
	val author: String,

	@field:SerializedName("price")
	val price: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("url")
	val url: String,

	@field:SerializedName("slug")
	val slug: String
)

data class Links(

	@field:SerializedName("next")
	val next: String,

	@field:SerializedName("last")
	val last: Any,

	@field:SerializedName("prev")
	val prev: Any,

	@field:SerializedName("first")
	val first: String
)
