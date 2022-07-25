package com.juned.expertclass.gramediabooks.favorite

import androidx.lifecycle.ViewModel
import com.juned.expertclass.gramediabooks.core.domain.usecase.BookUseCase

class FavoriteViewModel(bookUseCase: BookUseCase) : ViewModel() {
    val favoriteBook = bookUseCase.getFavoriteBook()
}