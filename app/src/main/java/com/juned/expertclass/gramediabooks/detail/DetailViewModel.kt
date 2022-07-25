package com.juned.expertclass.gramediabooks.detail

import androidx.lifecycle.ViewModel
import com.juned.expertclass.gramediabooks.core.domain.model.Book
import com.juned.expertclass.gramediabooks.core.domain.usecase.BookUseCase

class DetailViewModel(private val bookUseCase: BookUseCase) : ViewModel() {
    fun setFavoriteBook(tourism: Book, newStatus:Boolean) = bookUseCase.setFavoriteBook(tourism, newStatus)
}