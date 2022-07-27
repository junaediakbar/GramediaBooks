package com.juned.expertclass.gramediabooks.core.domain.repository

import androidx.lifecycle.LiveData
import com.juned.expertclass.gramediabooks.core.data.Resource
import com.juned.expertclass.gramediabooks.core.data.source.remote.response.BookDetail
import com.juned.expertclass.gramediabooks.core.domain.model.Book

interface IBookRepository {

    fun getAllBook(): LiveData<Resource<List<Book>>>

    fun getDetailBook(slug:String) :LiveData<Resource<Book>>

    fun getFavoriteBook(): LiveData<List<Book>>
    fun setFavoriteBook(book: Book, state: Boolean)

}