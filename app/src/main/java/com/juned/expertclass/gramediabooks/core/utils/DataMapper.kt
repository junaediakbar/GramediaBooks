package com.juned.expertclass.gramediabooks.core.utils

import com.juned.expertclass.gramediabooks.core.data.source.local.entity.BookEntity
import com.juned.expertclass.gramediabooks.core.data.source.remote.response.BookDetail
import com.juned.expertclass.gramediabooks.core.data.source.remote.response.BookResponse
import com.juned.expertclass.gramediabooks.core.data.source.remote.response.DetailBookResponse
import com.juned.expertclass.gramediabooks.core.domain.model.Book

object DataMapper {
    fun mapResponsesToEntities(input: List<BookResponse>): List<BookEntity> {
        val bookList = ArrayList<BookEntity>()
        input.map {
            val book = BookEntity(
                slug = it.slug,
                title = it.title,
                image = it.image,
                author= it.author,
                originalUrl = it.originalUrl,
                price = it.price,
                url = it.url,
                isFavorite = false
            )
            bookList.add(book)
        }
        return bookList
    }

    fun mapSingleEntitiesToDomain(input: BookEntity): Book = Book(
            slug = input.slug,
            title = input.title,
            image = input.image,
            author = input.author,
            originalUrl = input.originalUrl,
            price = input.price,
            url = input.url,
            description = input.description,
            isFavorite = input.isFavorite
        )

    fun mapEntitiesToDomain(input: List<BookEntity>): List<Book> =
        input.map {
            Book(
                slug = it.slug,
                title = it.title,
                image = it.image,
                author= it.author,
                originalUrl = it.originalUrl,
                price = it.price,
                url = it.url,
                description = "",
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Book) = BookEntity(
        slug = input.slug,
        title = input.title,
        image = input.image,
        author= input.author,
        originalUrl = input.originalUrl,
        price = input.price,
        url = input.url,
        isFavorite = input.isFavorite
    )

    fun mapDetailResponsesToEntities(input : BookDetail)=BookEntity(
        slug = input.slug,
        title = input.title,
        image = input.image,
        author= input.author,
        originalUrl = input.originalUrl,
        price = input.price,
        url = input.url,
        description = input.detail.description,
        isFavorite = false
    )
}