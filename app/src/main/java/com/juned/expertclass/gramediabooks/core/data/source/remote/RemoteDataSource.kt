package com.juned.expertclass.gramediabooks.core.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.juned.expertclass.gramediabooks.core.data.source.remote.network.ApiResponse
import com.juned.expertclass.gramediabooks.core.data.source.remote.network.ApiService
import com.juned.expertclass.gramediabooks.core.data.source.remote.response.BookResponse
import com.juned.expertclass.gramediabooks.core.data.source.remote.response.ListBookResponse
import com.juned.expertclass.gramediabooks.core.data.source.remote.response.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
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

}