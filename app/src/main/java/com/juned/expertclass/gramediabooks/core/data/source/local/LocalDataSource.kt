package com.juned.expertclass.gramediabooks.core.data.source.local

import androidx.lifecycle.LiveData
import com.juned.expertclass.gramediabooks.core.data.source.local.entity.BookEntity
import com.juned.expertclass.gramediabooks.core.data.source.local.room.BookDao

class LocalDataSource private constructor(private val bookDao: BookDao) {

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(tourismDao: BookDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(tourismDao)
            }
    }

    fun getAllBook(): LiveData<List<BookEntity>> = bookDao.getAllBook()

    fun getFavoriteBook(): LiveData<List<BookEntity>> = bookDao.getFavoriteBook()

    fun getBookById(slug: String): LiveData<BookEntity> = bookDao.getBookById(slug)

    fun updateBook(book: BookEntity) = bookDao.updateBook(book)

    fun insertTourism(tourismList: List<BookEntity>) = bookDao.insertBook(tourismList)

    fun setFavoriteBook(tourism: BookEntity, newState: Boolean) {
        tourism.isFavorite = newState
        bookDao.updateFavoriteBook(tourism)
    }
}