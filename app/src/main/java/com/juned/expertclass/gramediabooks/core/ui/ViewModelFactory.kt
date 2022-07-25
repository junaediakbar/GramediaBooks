package com.juned.expertclass.gramediabooks.core.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.juned.expertclass.gramediabooks.core.di.Injection
import com.juned.expertclass.gramediabooks.core.domain.usecase.BookUseCase
import com.juned.expertclass.gramediabooks.detail.DetailViewModel
import com.juned.expertclass.gramediabooks.favorite.FavoriteViewModel
import com.juned.expertclass.gramediabooks.home.HomeViewModel

class ViewModelFactory private constructor(private val bookUseCase: BookUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance
                ?: synchronized(this) {
                    instance
                        ?: ViewModelFactory(
                            Injection.provideBookUseCase(context)
                        )
                }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(bookUseCase) as T
            }
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(bookUseCase) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(bookUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}