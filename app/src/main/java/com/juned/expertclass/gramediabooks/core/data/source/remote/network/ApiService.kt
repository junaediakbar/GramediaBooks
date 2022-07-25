package com.juned.expertclass.gramediabooks.core.data.source.remote.network

import com.juned.expertclass.gramediabooks.core.data.source.remote.response.ListBookResponse
import com.juned.expertclass.gramediabooks.core.data.source.remote.response.LoginResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("token_name") token_name: String = "Public Mobile Token",
        @Field("expired_at") expired_at:String = "2030-12-31"
    ): LoginResponse

    @Headers("Authorization: Bearer 78|5eWgxm0PYM0wlDSctWJPAxdhRQy06tMBxIE0qh0g","Accept: application/json")
    @GET("books")
    fun getList(): Call<ListBookResponse>
}
