package com.juned.expertclass.gramediabooks.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.juned.expertclass.gramediabooks.core.data.source.local.entity.BookEntity
import com.juned.expertclass.gramediabooks.core.domain.model.Book

@Dao
interface BookDao {

    @Query("SELECT * FROM book")
    fun getAllBook(): LiveData<List<BookEntity>>

    @Query("SELECT * FROM book where isFavorite = 1")
    fun getFavoriteBook(): LiveData<List<BookEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBook(tourism: List<BookEntity>)

    @Query("SELECT * FROM book Where slug = :slug")
    fun getBookById(slug: String): LiveData<BookEntity>

    @Update
    fun updateFavoriteBook(tourism: BookEntity)

    @Update
    fun updateBook(book: BookEntity)
}
