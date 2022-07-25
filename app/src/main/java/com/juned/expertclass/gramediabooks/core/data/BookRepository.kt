package com.juned.expertclass.gramediabooks.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.juned.expertclass.gramediabooks.core.data.source.local.LocalDataSource
import com.juned.expertclass.gramediabooks.core.data.source.remote.RemoteDataSource
import com.juned.expertclass.gramediabooks.core.data.source.remote.network.ApiResponse
import com.juned.expertclass.gramediabooks.core.data.source.remote.response.BookResponse
import com.juned.expertclass.gramediabooks.core.domain.model.Book
import com.juned.expertclass.gramediabooks.core.domain.repository.IBookRepository
import com.juned.expertclass.gramediabooks.core.utils.AppExecutors
import com.juned.expertclass.gramediabooks.core.utils.DataMapper

class BookRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IBookRepository {

    companion object {
        @Volatile
        private var instance: BookRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): BookRepository =
            instance ?: synchronized(this) {
                instance ?: BookRepository(remoteData, localData, appExecutors)
            }
    }

    override fun getAllBook(): LiveData<Resource<List<Book>>> =
        object : NetworkBoundResource<List<Book>, List<BookResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<Book>> {
                return Transformations.map(localDataSource.getAllBook()) {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Book>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<BookResponse>>> =
                remoteDataSource.getAllBook()

            override fun saveCallResult(data: List<BookResponse>) {
                val tourismList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertTourism(tourismList)
            }
        }.asLiveData()

    override fun getFavoriteBook(): LiveData<List<Book>> {
        return Transformations.map(localDataSource.getFavoriteBook()){
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteBook(book: Book, state: Boolean) {
        val tourismEntity =DataMapper.mapDomainToEntity(book)
        appExecutors.diskIO().execute { localDataSource.setFavoriteBook(tourismEntity, state) }
    }

}