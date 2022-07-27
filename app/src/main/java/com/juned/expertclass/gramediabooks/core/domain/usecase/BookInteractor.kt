package com.juned.expertclass.gramediabooks.core.domain.usecase

import com.juned.expertclass.gramediabooks.core.domain.model.Book
import com.juned.expertclass.gramediabooks.core.domain.repository.IBookRepository

class BookInteractor(private val bookRepository: IBookRepository): BookUseCase {

    override fun getAllBook() = bookRepository.getAllBook()

    override fun getDetailBook(slug:String) = bookRepository.getDetailBook(slug)

    override fun getFavoriteBook() = bookRepository.getFavoriteBook()

    override fun setFavoriteBook(book: Book, state: Boolean) = bookRepository.setFavoriteBook(book, state)
}