package com.juned.expertclass.gramediabooks.core.di

import android.content.Context
import com.juned.expertclass.gramediabooks.core.data.BookRepository
import com.juned.expertclass.gramediabooks.core.data.source.local.LocalDataSource
import com.juned.expertclass.gramediabooks.core.data.source.local.room.BookDatabase
import com.juned.expertclass.gramediabooks.core.data.source.remote.RemoteDataSource
import com.juned.expertclass.gramediabooks.core.data.source.remote.network.ApiConfig
import com.juned.expertclass.gramediabooks.core.domain.repository.IBookRepository
import com.juned.expertclass.gramediabooks.core.domain.usecase.BookInteractor
import com.juned.expertclass.gramediabooks.core.domain.usecase.BookUseCase
import com.juned.expertclass.gramediabooks.core.utils.AppExecutors

object Injection {
    private fun provideRepository(context: Context): IBookRepository {
        val database = BookDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        val localDataSource = LocalDataSource.getInstance(database.bookDao())
        val appExecutors = AppExecutors()

        return BookRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideBookUseCase(context: Context): BookUseCase {
        val repository = provideRepository(context)
        return BookInteractor(repository)
    }
}