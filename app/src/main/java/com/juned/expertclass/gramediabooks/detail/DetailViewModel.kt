package com.juned.expertclass.gramediabooks.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.juned.expertclass.gramediabooks.core.domain.model.Book
import com.juned.expertclass.gramediabooks.core.domain.usecase.BookUseCase

class DetailViewModel(private val bookUseCase: BookUseCase) : ViewModel() {
    private var slug = MutableLiveData<String>()

    fun setBook(slug: String) {
        this.slug.value = slug
    }

    var book = Transformations.switchMap(slug) { slug ->
        bookUseCase.getDetailBook(slug)
    }

    fun setFavoriteBook(tourism: Book, newStatus:Boolean) = bookUseCase.setFavoriteBook(tourism, newStatus)

}