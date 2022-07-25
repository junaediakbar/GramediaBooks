package com.juned.expertclass.gramediabooks.home

import androidx.lifecycle.ViewModel
import com.juned.expertclass.gramediabooks.core.domain.usecase.BookUseCase

class HomeViewModel(bookUseCase: BookUseCase) : ViewModel() {
    val book = bookUseCase.getAllBook()
}
