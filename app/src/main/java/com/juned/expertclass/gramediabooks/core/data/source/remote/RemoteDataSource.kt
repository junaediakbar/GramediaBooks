package com.juned.expertclass.gramediabooks.core.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.juned.expertclass.gramediabooks.core.data.source.remote.network.ApiResponse
import com.juned.expertclass.gramediabooks.core.data.source.remote.network.ApiService
import com.juned.expertclass.gramediabooks.core.data.source.remote.response.BookDetail
import com.juned.expertclass.gramediabooks.core.data.source.remote.response.BookResponse
import com.juned.expertclass.gramediabooks.core.data.source.remote.response.DetailBookResponse
import com.juned.expertclass.gramediabooks.core.data.source.remote.response.ListBookResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource (private val apiService: ApiService){
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service)
            }
    }

    fun getAllBook(): LiveData<ApiResponse<List<BookResponse>>> {
        val resultData = MutableLiveData<ApiResponse<List<BookResponse>>>()

        //get data from API
        val client = apiService.getList()

        client.enqueue(object : Callback<ListBookResponse> {
            override fun onResponse(
                call: Call<ListBookResponse>,
                response: Response<ListBookResponse>
            ) {
                val dataArray = response.body()?.books
                resultData.value = if (dataArray != null) ApiResponse.Success(dataArray) else ApiResponse.Empty
            }
            override fun onFailure(call: Call<ListBookResponse>, t: Throwable) {
                resultData.value = ApiResponse.Error(t.message.toString())
                Log.e("RemoteDataSource", t.message.toString())
            }
        })

        return resultData
    }

    fun getDetailBook(slug: String): LiveData<ApiResponse<BookDetail>> {
        val resultData = MutableLiveData<ApiResponse<BookDetail>>()

        //get data from API
        val client = apiService.getDetailBook(slug)

        client.enqueue(object : Callback<DetailBookResponse> {
            override fun onResponse(
                call: Call<DetailBookResponse>,
                response: Response<DetailBookResponse>
            ) {
                val data= response.body()?.book
                resultData.value = if (data != null) ApiResponse.Success(data) else ApiResponse.Empty
            }
            override fun onFailure(call: Call<DetailBookResponse>, t: Throwable) {
                resultData.value = ApiResponse.Error(t.message.toString())
                Log.e("RemoteDataSource", t.message.toString())
            }
        })

        return resultData
    }



}