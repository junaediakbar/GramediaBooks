package com.juned.expertclass.gramediabooks.core.domain.usecase

import androidx.lifecycle.LiveData
import com.juned.expertclass.gramediabooks.core.data.Resource
import com.juned.expertclass.gramediabooks.core.data.source.remote.response.BookDetail
import com.juned.expertclass.gramediabooks.core.domain.model.Book

interface BookUseCase {
    fun getAllBook(): LiveData<Resource<List<Book>>>
    fun getDetailBook(slug:String): LiveData<Resource<Book>>
    fun getFavoriteBook(): LiveData<List<Book>>
    fun setFavoriteBook(tourism: Book, state: Boolean)
}