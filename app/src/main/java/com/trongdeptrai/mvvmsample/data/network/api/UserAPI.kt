package com.trongdeptrai.mvvmsample.data.network.api

import com.trongdeptrai.mvvmsample.data.network.responses.NetworkConnectionInternet
import com.trongdeptrai.mvvmsample.data.network.responses.UserResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserAPI {
    @FormUrlEncoded
    @POST("login.php")
    suspend fun userLogin(
        @Field("username") username: String,
        @Field("password") password: String
    ): Response<UserResponse>

    companion object {

        operator fun invoke(networkConnectionInternet: NetworkConnectionInternet): UserAPI {
            val client = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInternet).build()
            return Retrofit.Builder()
                .client(client)
                .baseUrl("http://192.85.4.97/mvvm/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UserAPI::class.java)
        }
    }
}